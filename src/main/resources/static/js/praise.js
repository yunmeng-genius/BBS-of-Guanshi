$(document).ready(function () {
    $(".praise-action").click(function () {
            let praiseOne = $(".glyphicon-user").text();                                        //获取username
            let logId = $(this).attr("praise-button");                                          //通过按钮的自添属性获取当前被点赞的log的id
            let flag = true;
            $(this).nextUntil(".help-praise").each((index, element) => {                        //遍历已新增的点赞人的username，判断当前用户是否已经点过赞
                if ($(element).text() === praiseOne) {
                    flag = false;
                }
            })
            $(this).nextAll(".praise-user").find(".old-praise-user").each((each,element)=>{     //遍历之前点过赞的人的username,判断当前用户是否已经点过赞
                if ($(element).text() === praiseOne) {
                    flag = false;
                }
            })
            if (flag) {                                                                         //如果当前用户从未给该条log点过赞，则可以执行ajax异步请求
                $.ajax({
                    url: "/praise",
                    type: 'post',
                    contentType: "application/json",
                    async: false,
                    data: JSON.stringify({
                        logId: logId
                    }),
                    complete: () => {                                                           //ajax请求后执行回调函数，新增元素显示
                        $(this).after(
                            "<span class='praise-user'>" + praiseOne + "</span>"
                        )
                    },
                })
            }
        }
    )
})