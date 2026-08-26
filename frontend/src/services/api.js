const BASE_URL = "http://localhost:8080";

async function request(url, options = {}) {

    const response = await fetch(BASE_URL + url, options);

    let data;

    try {
        data = await response.json();
    } catch {
        data = null;
    }

    if (!response.ok) {
        const validationDetails = data?.errors
            ? Object.values(data.errors).join("; ")
            : null;
        throw new Error(validationDetails || data?.message || "Request failed");
    }

    return data;
}

export { request };
