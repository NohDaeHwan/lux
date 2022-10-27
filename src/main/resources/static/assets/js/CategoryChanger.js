var Cswitch = 'big'
window.onload()
{
    categorySwitch(Cswitch);
}

function categorySwitch(tag)
{
    var type = {"categoryType" : tag}


    $.ajax({
        anyne: true,
        type: "POST",
        contentType: 'application/json',
        url: "/admin/product/category/change",

        data: {categoryType: tag},
        success: function (data) {
            console.log(data);
        }
    });

}
