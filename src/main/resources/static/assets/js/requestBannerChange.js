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
                    sector.children.item(0).children.item(0).innerHTML = "| 오늘";
                }
                else if(range === "month"){
                    sector.children.item(0).children.item(0).innerHTML = "| 이번달";
                }
                else if(range === "year"){
                    sector.children.item(0).children.item(0).innerHTML = "| 이번 년도";
                }
                sector.children.item(1).children.item(1).children.item(0).children.item(0).innerHTML = response["lresult"];
                console.log(response["lresult"]);
            }
            else if(banner === "revenueCard")
            {
                if(range === "today"){
                    sector.children.item(0).children.item(0).innerHTML = "| 오늘";
                }
                else if(range === "month"){
                    sector.children.item(0).children.item(0).innerHTML = "| 이번달";
                }
                else if(range === "year"){
                    sector.children.item(0).children.item(0).innerHTML = "| 이번 년도";
                }
                sector.children.item(1).children.item(1).children.item(0).children.item(0).innerHTML = response["lresult"];
                console.log(response["lresult"]);
            }
            else if(banner === "costomersCard")
            {
                if(range === "today"){
                    sector.children.item(0).children.item(0).innerHTML = "| 오늘";
                }
                else if(range === "month"){
                    sector.children.item(0).children.item(0).innerHTML = "| 이번달";
                }
                else if(range === "year"){
                    sector.children.item(0).children.item(0).innerHTML = "| 이번 년도";
                }
                sector.children.item(1).children.item(1).children.item(0).children.item(0).innerHTML = response["lresult"];
                console.log(response["lresult"]);
            }
            else if(banner === "recentSales")
            {
                if(range === "today"){
                    sector.children.item(0).children.item(0).innerHTML = "| 오늘";
                }
                else if(range === "month"){
                    sector.children.item(0).children.item(0).innerHTML = "| 이번달";
                }
                else if(range === "year"){
                    sector.children.item(0).children.item(0).innerHTML = "| 이번 년도";
                }
                let text = ["입찰완료","가장 높은 낙찰가","유찰","가장 높은 입찰가","높은 경쟁률"];
                let classist = ["bg-success","bg-info","bg-danger","bg-secondary","bg-warning"];
                let st = "";
                for(let i = 0 ; i < 5;i++)
                {
                  st +=  " <tr>" +
                "<th scope=\"row\"><a>"+response['auctions'][i]["auctionClosingDate"]
                    +"</a></th>" +
                "<td><a>"+response['auctions'][i]["bidder"]
                    +"</a></td>" +
                "<td><a class=\"text-primary\" href=\"/admin/product/product-detail/"+response['auctions'][i]["id"]+"\">"+response['auctions'][i]["id"]+"</a></td>" +
                "<td><a>"+response['auctions'][i]["presentPrice"]+"</a></td>" +
                "<td><span class=\"badge "+classist[i]+"\">"+text[i]+"</span></td>" +
                "</tr>";
                }
                sector.children.item(1).children.item(1).innerHTML = st;
                console.log(st);
            }
            else if(banner === "attentionPoint")
            {
                if(range === "today"){
                    sector.children.item(0).children.item(0).innerHTML = "| 오늘";
                }
                else if(range === "month"){
                    sector.children.item(0).children.item(0).innerHTML = "| 이번달";
                }
                else if(range === "year"){
                    sector.children.item(0).children.item(0).innerHTML = "| 이번 년도";
                }

                let  st = "  <td>" +
                    "<h4><a>"+ response["stResult"][0]+"</a></h4>" +
                    "<h6>가장 활발한 판매 경로</h6>" +
                    "</td>" +
                    "<td>" +
                    "<h4><a>"+ response["stResult"][1]+"</a></h4>" +
                    "<h6>가장 잘팔리는 큰 카테고리 유형</h6>" +
                    "</td>" +
                    "<td>" +
                    "<h4><a>"+ response["stResult"][2]+"</a></h4>" +
                    "<h6>가장 잘팔리는 작은 카테고리 유형</h6>" +
                    "</td>" +
                    "<td>" +
                    "<h4><a>"+ response["stResult"][3]+"</a></h4>" +
                    "<h6>가장 잘팔리는 가격대</h6>" +
                    "</td>" +
                    "<td>" +
                    "<h4><a>"+ response["stResult"][4]+"</a></h4>" +
                    "<h6>가장 많이 본 상품</h6>" +
                    "</td>";
                sector.children.item(1).children.item(1).children.item(0).innerHTML = st;

            }
            else if(banner === "costReport")
            {
                if(range === "today"){
                    sector.children.item(0).children.item(0).innerHTML = "| 오늘";
                }
                else if(range === "week"){
                    sector.children.item(0).children.item(0).innerHTML = "| 이번주";
                }
                else if(range === "month"){
                    sector.children.item(0).children.item(0).innerHTML = "| 이번달";
                }
                let st = "";
                let title = ["총수익","상품 수익","경매 수익","상품 순이익","경매 순이익","판매중인 상품","진행중인 경매"];
                //sector.children.item(1).children.item(0)
                for(let i = 0;i<7;i++)
                {
                    st +=
                        "<tr>" +
                        "<td><h4 class=\"card-title\">"+title[i]+"</h4></td>" +
                        "<td><h4 class=\"card-title\"><a>"+ response["stResult"][i]+"</a></h4></td>" +
                        "</tr>"

                }
                sector.children.item(1).children.item(0).innerHTML = st;

            }

        }

    }

}
