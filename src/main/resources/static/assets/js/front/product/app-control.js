const orderBtn = document.querySelectorAll('#orderBtn');

const prodOrder = (e) => {
    const prodId = document.querySelector('#prodId').value;
    const name = document.querySelector('#name').value;
    const phoneNumber = document.querySelector("#phoneNumber").value;
    const zoneCode = document.querySelector('#zoneCode').value;
    const addr = document.querySelector('#addr').value;
    const addrDetail = document.querySelector('#addrDetail').value;
    const payment = document.querySelector('#payment').value;
    const requestTerm = document.querySelector('#requestTerm').value;
    const data = {
        prodId: prodId,
        name: name,
        phoneNumber: phoneNumber,
        zoneCode: zoneCode,
        addr: `${addr} ${addrDetail}`,
        payment: payment,
        requestTerm: requestTerm,
    }

    productOrder(data).then((res) => {
        console.log(res);
        location.href = `/product/success`;
    }).catch((error) => {
        console.log(error);
    })
}
orderBtn.forEach((item) => item.addEventListener('click', prodOrder));
