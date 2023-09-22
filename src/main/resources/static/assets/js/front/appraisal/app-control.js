const appSell = document.querySelectorAll('#app-sell');
const appRefuse = document.querySelectorAll('#app-refuse');

function changeState(state) {
    const appraisalId = document.querySelector('#appraisalId').value;
    let data = {
        appraisalState: state,
        appraisalId: appraisalId
    }
    adminDroneDelete(data).then((res) => {
        location.href = `/appraisal/${data.appraisalId}`;
    }).catch((error) => {
        console.log(error);
    })
}

appSell.forEach((item) => item.addEventListener('click', () => {changeState('SELL')}));
appRefuse.forEach((item) => item.addEventListener('click', () => {changeState('REFUSE_TO_SELL')}));
