#!/bin/sh
set -eu

serial="${ANDROID_SERIAL:-android-emulator:5555}"
deadline=$(( $(date +%s) + ${EMULATOR_BOOT_TIMEOUT_SECONDS:-300} ))

until adb connect "$serial" >/dev/null 2>&1 && adb -s "$serial" get-state 2>/dev/null | grep -qx device; do
  [ "$(date +%s)" -lt "$deadline" ] || { echo "Timed out waiting for ADB at $serial" >&2; exit 1; }
  sleep 2
done

adb -s "$serial" wait-for-device
until [ "$(adb -s "$serial" shell getprop sys.boot_completed 2>/dev/null | tr -d '\r')" = "1" ]; do
  [ "$(date +%s)" -lt "$deadline" ] || { echo "Timed out waiting for Android boot" >&2; exit 1; }
  sleep 2
done

test -s /apk/app-debug.apk
adb -s "$serial" install -r /apk/app-debug.apk
adb -s "$serial" shell pm path com.qastand.android | grep -q '^package:'
touch /tmp/apk-installed
echo "Emulator booted and com.qastand.android installed"
exec tail -f /dev/null
