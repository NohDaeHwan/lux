console.log("i'm loaded")

    function save_btn() {

    let data = {
    appraisalName: $('#appraisalName').val(),
    appraisalPrice: $('#appraisalPrice').val(),
    appraisalBrand: $("select[name=appraiseBrand]").val(),
    appraisalGender: $('#appraisalGender').val(),
    appraisalColor: $('#appraisalColor').val(),
    appraisalSize: $('#appraisalSize').val(),
    appraisalGrade: $('#appraisalGrade').val(),
    appraisalComment: $('#appraisalComment').val(),
    appraisalId: $('#appraisalId').text(),
    appraisalStateId: 2
}
    console.log(data);
    $.ajax({
    type:"POST",
    url:"/admin/appraise/"+[[${appraisalResponse.id}]]+"/new/loading",
    data:JSON.stringify(data),
    contentType:"application/json; charset=utf-8",
    dataType:"json",
}).done(function(res){
    console.log(res+"functifadslkfjasldfjk")
    location.href="/admin/appraise";
}).fail(function(error){
    console.log("function error : "+error);
});
}

    function complete_btn() {
    let data = {
    appraisalName: $('#appraisalName').val(),
    appraisalPrice: $('#appraisalPrice').val(),
    appraisalBrand: $("select[name=appraiseBrand]").val(),
    appraisalGender: $('#appraisalGender').val(),
    appraisalColor: $('#appraisalColor').val(),
    appraisalSize: $('#appraisalSize').val(),
    appraisalGrade: $('#appraisalGrade').val(),
    appraisalComment: $('#appraisalComment').val(),
    appraisalId: $('#appraisalId').text(),
    appraisalStateId: 3
}
    console.log(data);
    $.ajax({
    type:"POST",
    url:"/admin/appraise/"+[[${appraisalResponse.id}]]+"/new/loading",
    data:JSON.stringify(data),
    contentType:"application/json; charset=utf-8",
    dataType:"json",
}).done(function(res){
    location.href="/admin/appraise";
}).fail(function(error){
    console.log("function error : "+error);
});
}
