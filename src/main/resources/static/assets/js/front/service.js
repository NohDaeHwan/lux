const adminDroneDelete = async (data) => {
    return await fetch(`/appraisal/${data.appraisalId}/change`, {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
}

const productSave = async (form) => {
    return await fetch(`/admin/product/save`, {
        method: 'POST',
        cache: 'no-cache',
        body: form
    });
}

const cateSearch = async (data) => {
    const response = await fetch(`/cate/${data}`, {
        method: 'POST',
        cache: 'no-cache',
    });

    return response.json();
}
