const findAddrBtn = document.querySelector('#findAddrBtn');

const findAddr = () => {
    new daum.Postcode({
        oncomplete: function(data) {
            const zoneCode = data.zonecode;
            const roadAddr = data.roadAddress;

            document.querySelector('#zoneCode').value = zoneCode;
            document.querySelector('#addr').value = roadAddr;
        }
    }).open();
}

findAddrBtn.addEventListener('click', findAddr);
