const productSave = async (form) => {
    return await fetch(`/admin/product/save`, {
        method: 'POST',
        cache: 'no-cache',
        body: form
    });
}

const getAppraisal = async (appraisalId) => {
    const response = await fetch(`/appraisal/${appraisalId}`, {
        method: 'POST',
        cache: 'no-cache',
    });

    return response.json();
}

const cateSearch = async (data) => {
    const response = await fetch(`/cate/${data}`, {
        method: 'POST',
        cache: 'no-cache',
    });

    return response.json();
}
