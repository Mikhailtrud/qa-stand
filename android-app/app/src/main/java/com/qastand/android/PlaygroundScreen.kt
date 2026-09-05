package com.qastand.android

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import java.util.Calendar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PlaygroundScreen(onUsers: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .appiumTag("playground_screen"),
    ) {
        Text("QA Playground", style = MaterialTheme.typography.headlineMedium)
        Button(
            onClick = onUsers,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .appiumTag("open_users_button"),
        ) {
            Text("Users")
        }
        FormsSection()
        DialogsSection()
        TabsSection()
        TablesSection()
        DynamicSection()
        ActionsSection()
    }
}

@Composable
private fun PlaygroundSection(title: String, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 12.dp),
            )
            content()
        }
    }
}

@Composable
private fun FormsSection() {
    var text by remember { mutableStateOf("") }
    var textarea by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var select by remember { mutableStateOf("Option 1") }
    var selectExpanded by remember { mutableStateOf(false) }
    var selectedLanguages by remember { mutableStateOf(setOf<String>()) }
    var multiExpanded by remember { mutableStateOf(false) }
    var accepted by remember { mutableStateOf(false) }
    var gender by remember { mutableStateOf<String?>(null) }
    var selectedFile by remember { mutableStateOf("No file selected") }
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }
    val fileLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        selectedFile = uri?.lastPathSegment ?: "No file selected"
    }

    PlaygroundSection("Forms") {
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Text") },
        placeholder = { Text("Type something") },
        modifier = Modifier.fillMaxWidth().appiumEditableTag("playground_text_input", text) { text = it },
        singleLine = true,
    )
    OutlinedTextField(
        value = textarea,
        onValueChange = { textarea = it },
        label = { Text("Textarea") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .appiumEditableTag("playground_textarea", textarea) { textarea = it },
        minLines = 4,
    )
    Button(
        onClick = {
            DatePickerDialog(
                context,
                { _, year, month, day -> date = "%04d-%02d-%02d".format(year, month + 1, day) },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
            ).show()
        },
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp).appiumTag("playground_date"),
    ) {
        Text(if (date.isEmpty()) "Choose date" else date)
    }
    Box {
        Button(
            onClick = { selectExpanded = true },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp).appiumTag("playground_select"),
        ) {
            Text(select)
        }
        DropdownMenu(expanded = selectExpanded, onDismissRequest = { selectExpanded = false }) {
            listOf("Option 1", "Option 2", "Option 3").forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        select = option
                        selectExpanded = false
                    },
                    modifier = Modifier.appiumTag("select_${option.lowercase().replace(' ', '_')}")
                )
            }
        }
    }
    Box {
        Button(
            onClick = { multiExpanded = true },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp).appiumTag("playground_multiselect"),
        ) {
            Text(if (selectedLanguages.isEmpty()) "Multi Select" else selectedLanguages.joinToString())
        }
        DropdownMenu(expanded = multiExpanded, onDismissRequest = { multiExpanded = false }) {
            playgroundLanguages.forEach { language ->
                val selected = selectedLanguages.contains(language)
                DropdownMenuItem(
                    text = {
                        Row(
                            modifier = Modifier.clearAndSetSemantics {
                                contentDescription = "multiselect_${language.tagValue()}"
                                role = Role.Checkbox
                                toggleableState = if (selected) {
                                    ToggleableState.On
                                } else {
                                    ToggleableState.Off
                                }
                            },
                        ) {
                            Checkbox(
                                checked = selected,
                                onCheckedChange = null,
                            )
                            Text(language)
                        }
                    },
                    onClick = {
                        selectedLanguages = if (selected) {
                            selectedLanguages - language
                        } else {
                            selectedLanguages + language
                        }
                    },
                )
            }
        }
    }
    Button(
        onClick = { fileLauncher.launch("*/*") },
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp).appiumTag("playground_file_upload"),
    ) {
        Text("Choose file")
    }
    Text(selectedFile, modifier = Modifier.appiumTag("selected_file_name"))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .toggleable(accepted, role = Role.Checkbox) { accepted = it }
            .appiumTag("playground_checkbox"),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(checked = accepted, onCheckedChange = null)
        Text("Accept Terms")
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        RadioButton(
            selected = gender == "Male",
            onClick = { gender = "Male" },
            modifier = Modifier.appiumTag("radio_male"),
        )
        Text("Male", modifier = Modifier.padding(top = 12.dp))
        RadioButton(
            selected = gender == "Female",
            onClick = { gender = "Female" },
            modifier = Modifier.appiumTag("radio_female"),
        )
        Text("Female", modifier = Modifier.padding(top = 12.dp))
    }
    }
}

private val playgroundLanguages = listOf(
    "Java", "Kotlin", "Scala", "Groovy", "Python", "JavaScript", "TypeScript", "C#",
    "C++", "Go", "Rust", "PHP", "Ruby", "Swift", "Dart", "SQL", "Bash", "Docker",
    "Kubernetes", "Spring Boot",
)

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun DialogsSection() {
    var dialog by remember { mutableStateOf<String?>(null) }
    var promptText by remember { mutableStateOf("") }
    var toastMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    PlaygroundSection("JavaScript") {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Button(onClick = { dialog = "alert" }, modifier = Modifier.appiumTag("playground_alert_button")) {
                Text("Alert")
            }
            Button(onClick = { dialog = "confirm" }, modifier = Modifier.appiumTag("playground_confirm_button")) {
                Text("Confirm")
            }
            Button(onClick = { dialog = "prompt" }, modifier = Modifier.appiumTag("playground_prompt_button")) {
                Text("Prompt")
            }
            Button(
                onClick = {
                    val message = "Operation completed successfully"
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    toastMessage = message
                    scope.launch {
                        delay(5_000)
                        toastMessage = null
                    }
                },
                modifier = Modifier.appiumTag("playground_toast_button"),
            ) {
                Text("Toast")
            }
            Button(onClick = { dialog = "modal" }, modifier = Modifier.appiumTag("open_modal_button")) {
                Text("Modal")
            }
        }
        toastMessage?.let { message ->
            Text(
                text = message,
                modifier = Modifier.appiumTag("toast_message"),
            )
        }
    }

    when (dialog) {
        "alert" -> PlaygroundDialog("Test Alert", "", "alert_dialog", { dialog = null })
        "confirm" -> PlaygroundDialog("Are you sure?", "", "confirm_dialog", { dialog = null })
        "modal" -> PlaygroundDialog(
            "Modal Window",
            "This modal is used for UI automation testing.",
            "modal_window",
            { dialog = null },
        )
        "prompt" -> AlertDialog(
            modifier = Modifier.appiumTag("prompt_dialog"),
            onDismissRequest = { dialog = null },
            title = { Text("Enter your name") },
            text = {
                OutlinedTextField(
                    value = promptText,
                    onValueChange = { promptText = it },
                    modifier = Modifier.appiumEditableTag("prompt_input", promptText) { promptText = it },
                )
            },
            confirmButton = {
                TextButton(onClick = { dialog = null }, modifier = Modifier.appiumTag("prompt_ok_button")) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { dialog = null },
                    modifier = Modifier.appiumTag("prompt_cancel_button"),
                ) {
                    Text("Cancel")
                }
            },
        )
    }
}

@Composable
private fun PlaygroundDialog(title: String, text: String, tag: String, onClose: () -> Unit) {
    AlertDialog(
        modifier = Modifier.appiumTag(tag),
        onDismissRequest = onClose,
        title = { Text(title) },
        text = { if (text.isNotEmpty()) Text(text) },
        confirmButton = {
            TextButton(onClick = onClose, modifier = Modifier.appiumTag("${tag}_close_button")) {
                Text(if (tag == "modal_window") "Close" else "OK")
            }
        },
        dismissButton = if (tag == "confirm_dialog") {
            {
                TextButton(
                    onClick = onClose,
                    modifier = Modifier.appiumTag("confirm_dialog_cancel_button"),
                ) {
                    Text("Cancel")
                }
            }
        } else null,
    )
}

@Composable
private fun TabsSection() {
    var activeTab by remember { mutableIntStateOf(1) }
    PlaygroundSection("Tabs") {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        (1..3).forEach { tab ->
            Button(
                onClick = { activeTab = tab },
                modifier = Modifier.width(96.dp).appiumTag("tab_${tab}_button"),
            ) {
                Text("Tab $tab")
            }
        }
    }
    Text(
        "Content Tab $activeTab",
        modifier = Modifier.padding(top = 8.dp).appiumTag("tab_content"),
    )
    }
}

private data class PlaygroundRow(val id: Int, val name: String, val role: String)

@Composable
private fun TablesSection() {
    var search by remember { mutableStateOf("") }
    var roleFilter by remember { mutableStateOf("ALL") }
    var filterExpanded by remember { mutableStateOf(false) }
    var sortField by remember { mutableStateOf("id") }
    var ascending by remember { mutableStateOf(true) }
    var page by remember { mutableIntStateOf(1) }
    val allRows = remember {
        listOf(
            PlaygroundRow(1, "John", "ADMIN"), PlaygroundRow(2, "Kate", "USER"),
            PlaygroundRow(3, "Mike", "USER"), PlaygroundRow(4, "Sara", "ADMIN"),
            PlaygroundRow(5, "Tom", "USER"),
        )
    }
    val filtered = allRows
        .filter { it.name.contains(search, ignoreCase = true) }
        .filter { roleFilter == "ALL" || it.role == roleFilter }
        .sortedWith { first, second ->
            val comparison = when (sortField) {
                "name" -> first.name.lowercase().compareTo(second.name.lowercase())
                "role" -> first.role.lowercase().compareTo(second.role.lowercase())
                else -> first.id.compareTo(second.id)
            }
            if (ascending) comparison else -comparison
        }
    val totalPages = maxOf(1, (filtered.size + 1) / 2)
    val visibleRows = filtered.drop((page - 1) * 2).take(2)

    fun changeSort(field: String) {
        if (sortField == field) ascending = !ascending else {
            sortField = field
            ascending = false
        }
    }

    PlaygroundSection("Tables") {
    OutlinedTextField(
        value = search,
        onValueChange = { search = it; page = 1 },
        label = { Text("Search") },
        modifier = Modifier.fillMaxWidth().appiumEditableTag("table_search", search) { search = it; page = 1 },
        singleLine = true,
    )
    Box {
        Button(
            onClick = { filterExpanded = true },
            modifier = Modifier.fillMaxWidth().appiumTag("role_filter"),
        ) { Text("Role: $roleFilter") }
        DropdownMenu(expanded = filterExpanded, onDismissRequest = { filterExpanded = false }) {
            listOf("ALL", "ADMIN", "USER").forEach { role ->
                DropdownMenuItem(
                    text = { Text(role) },
                    onClick = { roleFilter = role; page = 1; filterExpanded = false },
                    modifier = Modifier.appiumTag("role_filter_${role.lowercase()}"),
                )
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
    ) {
        Column(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .appiumTag("dynamic_table"),
        ) {
            Row(modifier = Modifier.background(MaterialTheme.colorScheme.surfaceVariant)) {
                Button(onClick = { changeSort("id") }, modifier = Modifier.width(72.dp).appiumTag("sort_id")) { Text("ID") }
                Button(onClick = { changeSort("name") }, modifier = Modifier.width(132.dp).appiumTag("sort_name")) { Text("Name") }
                Button(onClick = { changeSort("role") }, modifier = Modifier.width(104.dp).appiumTag("sort_role")) { Text("Role") }
            }
            visibleRows.forEachIndexed { index, row ->
                Row(
                    modifier = Modifier
                        .background(if (index % 2 == 0) Color.Transparent else MaterialTheme.colorScheme.surfaceVariant)
                        .appiumTag("table_row_${row.id}"),
                ) {
                    Text("${row.id}", modifier = Modifier.width(72.dp).padding(12.dp))
                    Text(row.name, modifier = Modifier.width(132.dp).padding(12.dp))
                    Text(row.role, modifier = Modifier.width(104.dp).padding(12.dp))
                }
            }
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Button(
            onClick = { page-- },
            enabled = page > 1,
            modifier = Modifier.width(108.dp).appiumTag("previous_page_button"),
        ) { Text("Previous") }
        Text("$page / $totalPages", modifier = Modifier.padding(12.dp).appiumTag("current_page"))
        Button(
            onClick = { page++ },
            enabled = page < totalPages,
            modifier = Modifier.width(108.dp).appiumTag("next_page_button"),
        ) { Text("Next") }
    }
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun DynamicSection() {
    var showLoader by remember { mutableStateOf(false) }
    var showDelayedButton by remember { mutableStateOf(false) }
    var showHidden by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        delay(5_000)
        showHidden = true
    }
    PlaygroundSection("Dynamic Elements") {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
    Button(
        onClick = {
            scope.launch {
                showLoader = true
                delay(5_000)
                showLoader = false
            }
        },
        modifier = Modifier.appiumTag("start_loader_button"),
    ) { Text("Start Loader") }
    Button(
        onClick = {
            scope.launch {
                showDelayedButton = false
                delay(3_000)
                showDelayedButton = true
            }
        },
        modifier = Modifier.appiumTag("show_delayed_button"),
    ) { Text("Show Delayed Button") }
    }
    if (showLoader) {
        Row {
            CircularProgressIndicator()
            Text(
                "Loading...",
                modifier = Modifier
                    .padding(12.dp)
                    .appiumTag("loader"),
            )
        }
    }
    if (showDelayedButton) {
        Button(onClick = {}, modifier = Modifier.appiumTag("delayed_button")) { Text("Delayed Button") }
    }
    if (showHidden) {
        Text("Hidden element appeared.", modifier = Modifier.appiumTag("hidden_element"))
    }
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun ActionsSection() {
    var message by remember { mutableStateOf("No action") }
    PlaygroundSection("Mouse Actions") {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
    Button(
        onClick = { message = "Hover" },
        modifier = Modifier.appiumTag("hover_button"),
    ) { Text("Hover (tap)") }
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary, MaterialTheme.shapes.extraLarge)
            .pointerInput(Unit) { detectTapGestures(onDoubleTap = { message = "Double Click" }) }
            .appiumTag("double_click_button"),
    ) {
        Text(
            "Double Click",
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
        )
    }
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary, MaterialTheme.shapes.extraLarge)
            .pointerInput(Unit) { detectTapGestures(onLongPress = { message = "Right Click" }) }
            .appiumTag("right_click_button"),
    ) {
        Text(
            "Right Click (long press)",
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
        )
    }
    }
    Text(message, modifier = Modifier.padding(top = 8.dp).appiumTag("mouse_action_result"))
    }
}

private fun String.tagValue(): String = lowercase()
    .replace("#", "sharp")
    .replace("+", "plus")
    .replace(" ", "_")
