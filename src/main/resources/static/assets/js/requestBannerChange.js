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
        }

    }

}
