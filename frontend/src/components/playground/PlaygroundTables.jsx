import { useMemo, useState } from "react";

import "../../styles/playground.css";

const PAGE_SIZE = 2;

function PlaygroundTables() {

    const [page, setPage] = useState(1);
    const [search, setSearch] = useState("");
    const [roleFilter, setRoleFilter] = useState("ALL");
    const [sortField, setSortField] = useState("id");
    const [sortDirection, setSortDirection] = useState("asc");

    const tableData = [
        { id: 1, name: "John", role: "ADMIN" },
        { id: 2, name: "Kate", role: "USER" },
        { id: 3, name: "Mike", role: "USER" },
        { id: 4, name: "Sara", role: "ADMIN" },
        { id: 5, name: "Tom", role: "USER" }
    ];

    function changeSort(field) {

        if (sortField === field) {
            setSortDirection(sortDirection === "asc" ? "desc" : "asc");
        } else {
            setSortField(field);
            setSortDirection("asc");
        }

    }

    const filteredRows = useMemo(() => {

        let result = [...tableData];

        if (search.trim() !== "") {
            result = result.filter(user =>
                user.name.toLowerCase().includes(search.toLowerCase())
            );
        }

        if (roleFilter !== "ALL") {
            result = result.filter(user => user.role === roleFilter);
        }

        result.sort((a, b) => {

            let first = a[sortField];
            let second = b[sortField];

            if (typeof first === "string") {
                first = first.toLowerCase();
                second = second.toLowerCase();
            }

            if (first < second) return sortDirection === "asc" ? -1 : 1;
            if (first > second) return sortDirection === "asc" ? 1 : -1;

            return 0;

        });

        return result;

    }, [search, roleFilter, sortField, sortDirection]);

    const totalPages = Math.max(1, Math.ceil(filteredRows.length / PAGE_SIZE));

    const rows = filteredRows.slice(
        (page - 1) * PAGE_SIZE,
        page * PAGE_SIZE
    );

    return (

        <section className="playground-section" data-testid="playground-tables-section">

            <h2>Tables</h2>

            <div className="playground-grid">

                <div className="playground-field">

                    <label>Search</label>

                    <input
                        data-testid="table-search"
                        value={search}
                        onChange={(e) => {
                            setSearch(e.target.value);
                            setPage(1);
                        }}
                    />

                </div>

                <div className="playground-field">

                    <label>Role</label>

                    <select
                        data-testid="role-filter"
                        value={roleFilter}
                        onChange={(e) => {
                            setRoleFilter(e.target.value);
                            setPage(1);
                        }}
                    >

                        <option value="ALL">ALL</option>
                        <option value="ADMIN">ADMIN</option>
                        <option value="USER">USER</option>

                    </select>

                </div>

            </div>

            <br />

            <table
                className="playground-table"
                data-testid="dynamic-table"
            >

                <thead>

                <tr>

                    <th
                        data-testid="sort-id"
                        onClick={() => changeSort("id")}
                    >
                        ID
                    </th>

                    <th
                        data-testid="sort-name"
                        onClick={() => changeSort("name")}
                    >
                        Name
                    </th>

                    <th
                        data-testid="sort-role"
                        onClick={() => changeSort("role")}
                    >
                        Role
                    </th>

                </tr>

                </thead>

                <tbody>

                {rows.map((row) => (

                    <tr
                        key={row.id}
                        data-testid={`table-row-${row.id}`}
                    >
                        <td>{row.id}</td>
                        <td>{row.name}</td>
                        <td>{row.role}</td>
                    </tr>

                ))}

                </tbody>

            </table>

            <br />

            <div className="playground-buttons">

                <button
                    data-testid="page-1"
                    disabled={page === 1}
                    onClick={() => setPage(page - 1)}
                >
                    Previous
                </button>

                <span data-testid="current-page">
                    {page} / {totalPages}
                </span>

                <button
                    data-testid="page-2"
                    disabled={page === totalPages}
                    onClick={() => setPage(page + 1)}
                >
                    Next
                </button>

            </div>

        </section>

    );

}

export default PlaygroundTables;
