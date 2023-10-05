const changeStateService = async (data) => {
    return await fetch(`/appraisal/${data.appraisalId}/change`, {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
}

const productOrder = async (data) => {
    return await fetch(`/product/order/loading`, {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data),
    });
}
