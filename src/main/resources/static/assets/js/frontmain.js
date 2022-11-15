const inputLeft = document.getElementById("input-left");
const inputRight = document.getElementById("input-right");

const thumbLeft = document.querySelector(".thumb.left");
const thumbRight = document.querySelector(".thumb.right");

const range = document.querySelector(".range");

const setLeftValue = e => {
    const _this = e.target;

    const { value, min, max } = _this;

    if (+inputRight.value - +value < 10) {
        _this.value = +inputRight.value - 10;
    }

    if (+inputRight.value - +inputLeft <= 0){
        _this.value = +inputRight.value - 1;
    }

    const percent = ((+_this.value - +min) / (+max - +min)) * 100;

    //최솟값
    document.getElementById('value1').innerHTML = _this.value * 10000;
    document.getElementById('minPrice').value = _this.value * 10000;

    thumbLeft.style.left = `${percent}%`;
    range.style.left = `${percent}%`;
};

const setRightValue = e => {
    const _this = e.target;
    const { value, min, max } = _this;

    if (+value - +inputLeft.value < 10) {
        _this.value = +inputLeft.value + 10;
    }

    if (+inputRight.value - +inputLeft >= 0){
        _this.value = +inputRight.value + 1;
    }

    const percent = ((+_this.value - +min) / (+max - +min)) * 100;
    thumbRight.style.right = `${100 - percent}%`;
    range.style.right = `${100 - percent}%`;

    //최대값
    document.getElementById('value2').innerHTML = _this.value * 100000;
    document.getElementById('maxPrice').value = _this.value * 100000;
};

if (inputLeft && inputRight) {
    inputLeft.addEventListener("input", setLeftValue);
    inputRight.addEventListener("input", setRightValue);
}


