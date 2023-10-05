const sellTypeUsed = document.querySelectorAll('#sellTypeUsed');
const sellTypeAuction = document.querySelectorAll('#sellTypeAuction');
const categoryBId = document.querySelectorAll('#categoryBId');
const prodRegisterBtn= document.querySelectorAll('#prod-register');

let imageFileList = [];
//드래그해서 파일 받기
let imageViewer = document.getElementById("dragArea");
//input을 이용한 이미지 파일 전송
let imageFiles = document.getElementById("uploader");
let a = document.getElementById("a");


imageViewer.ondragover = (e) => {
    e.preventDefault();
};

imageViewer.ondrop = (e) => {
    e.preventDefault();

    let data = e.dataTransfer.files;
    let img;
    for(let i =0;i<data.length;i++) {
        if (data[i].type.match("image.*")) {
            img = document.createElement("img");
            img.src = window.URL.createObjectURL(data[i]);
            img.style.outline = "none";
            img.style.width = "225px";
            img.style.height = "225px";
            img.style.display = "flex";
            img.classList += "p-2";
            a.appendChild(img);
            imageFileList.push(data[i]);
        } else {
            console.log("데이터 유형이 이미지파일이 아닙니다.");
        }
    }

}

imageFiles.onchange = (e) => {
    for(let i =0;i<imageFiles.files.length;i++) {
        let img =  document.createElement("img");
        img.src = window.URL.createObjectURL(imageFiles.files[i]);
        img.style.outline = "none";
        img.style.width = "225px";
        img.style.height = "225px";
        img.style.display = "flex";
        img.classList += "p-2";

        a.appendChild(img);
        imageFileList.push(imageFiles.files[i]);
    }
}

const formToggle = (type) => {
    if (type === 'used') document.querySelector('#auctionForm').style.display = 'none';
    else if (type === 'auction') document.querySelector('#auctionForm').style.display = 'block';
}

const changeCateM = () => {
    const cateB = document.querySelector("#categoryBId");
    const cateBId = cateB.options[cateB.selectedIndex].value;

    cateSearch(cateBId).then((res) => {
        const cateM = document.querySelector("#categoryMId");
        cateM.innerHTML = `<option value="">대분류 카테고리를 선택해주세요.</option>`;
        res.forEach((item) => {
            cateM.innerHTML += `<option value="${item.id}">${item.cateMNm}</option>`;
        })
    }).catch((error) => {
        console.log(error);
    })
}

const prodRegister = (e) => {
    const appraisal = document.querySelector('#appraisalList tr.selected').id;
    if (appraisal === null) {
        return alert('매입 상품을 선택해주세요.');
    }
    const productSellType = document.getElementsByName("productSellType");
    const selected = Array.from(productSellType).find(radio => radio.checked);
    const prodNm = document.querySelector('#prodNm').value;
    const cateB = document.querySelector("#categoryBId");
    const cateBId = cateB.options[cateB.selectedIndex].value;
    const cateM = document.querySelector("#categoryMId");
    const cateMId = cateM.options[cateM.selectedIndex].value;
    const prodBrand = document.querySelector("#prodBrand");
    const prodBrandId = prodBrand.options[prodBrand.selectedIndex].value;
    const prodSize = document.querySelector('#prodSize').value;
    const prodGender = document.querySelector("#prodGender");
    const prodGenderId = prodGender.options[prodGender.selectedIndex].value;
    const prodColor = document.querySelector('#prodColor').value;
    const prodGrade = document.querySelector("#prodGrade");
    const prodGradeId = prodGrade.options[prodGrade.selectedIndex].value;
    const prodPrice = document.querySelector('#prodPrice').value;
    const prodContent = document.querySelector('#prodContent').value;

    const startPrice = document.querySelector('#startPrice').value;
    const aucStartDate = document.querySelector('#aucStartDate').value;
    const aucEndDate = document.querySelector('#aucEndDate').value;

    const form = new FormData();
    form.append("appraisalId", appraisal.split('-')[1]);
    form.append("prodSellType", selected.value);
    form.append("prodNm", prodNm);
    form.append("cateBId", cateBId);
    form.append("cateMId", cateMId);
    form.append("prodBrandId", prodBrandId);
    form.append("prodSize", prodSize);
    form.append("prodGenderId", prodGenderId);
    form.append("prodColor", prodColor);
    form.append("prodGradeId", prodGradeId);
    form.append("prodPrice", prodPrice);
    form.append("prodContent", prodContent);
    form.append("startPrice", startPrice);
    form.append("aucStartDate", aucStartDate);
    form.append("aucEndDate", aucEndDate);
    imageFileList.forEach(item => form.append("images", item));

    productSave(form).then((res) => {
        console.log(res);
        if (res.status === 200) {
            alert("판매 상품을 등록했습니다.");
            location.href = `/admin/product`;
        }
    }).catch((error) => {
        console.log(error);
    })
}

const rowSelected = (id) => {
    document.getElementById(`appraisal-${id}`).classList.add("selected");
    document.querySelector('#prodForm').style.display = 'block';
    document.querySelector('#prod-register').style.display = 'block';

    getAppraisal(id).then((res) => {
        console.log(res);
        document.querySelector('#prodNm').value = res.appProdNm;
        $('#prodBrand').val(res.appBrandId).prop('selected', true);
        $('#prodGender').val(res.appGender).prop('selected', true);
        $('#prodGrade').val(res.appGrade).prop('selected', true);
        document.querySelector('#prodSize').value = res.appSize;
        document.querySelector('#prodColor').value = res.appColor;
        document.querySelector('#prodPrice').value = res.appPrice;
    }).catch((error) => {
        console.log(error);
    })
}

sellTypeUsed.forEach((item) => item.addEventListener('click', () => {formToggle('used')}));
sellTypeAuction.forEach((item) => item.addEventListener('click', () => {formToggle('auction')}));
categoryBId.forEach((item) => item.addEventListener('change', changeCateM));
prodRegisterBtn.forEach((item) => item.addEventListener('click', prodRegister));
