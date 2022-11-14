function bannerChanger(banner,range)
{
    let data = {
        "banner" : banner,
        "bannerDateType" : range
    };
    let sender = new XMLHttpRequest;
    sender.open("POST","/admin/requestBannerChange",true);
    sender.setRequestHeader('Content-Type', 'application/json');
    sender.send(JSON.stringify(data));

    sender.onload = () =>{
        if(sender.status === 200)
        {
            let response = JSON.parse(sender.response);
            let sector = document.getElementById(banner);

            if(banner === "salesCard")
            {
                if(range === "today"){
                    sector.children.item(0).children.item(0).innerHTML = "| 오늘"
                }
                else if(range === "month"){
                    sector.children.item(0).children.item(0).innerHTML = "| 이번달"
                }
                else if(range === "year"){
                    sector.children.item(0).children.item(0).innerHTML = "| 이번 년도"
                }

                console.log(response["lresult"]);
            }
            else if(banner === "revenueCard")
            {
                console.log(response["lresult"]);
            }
            else if(banner === "costomersCard")
            {
                console.log(response["lresult"]);
            }
            else if(banner === "recentSales")
            {
                console.log(response["auctions"]);
            }
            else if(banner === "attentionPoint")
            {
                console.log(response["stResult"]);
            }
            else if(banner === "costReport")
            {
                console.log(response["stResult"]);
            }

        }

    }

}
