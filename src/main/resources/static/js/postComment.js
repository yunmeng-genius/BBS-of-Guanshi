$(document).ready(function () {
    $(".blog-button").click(function () {
        let str1 = $(this).attr("blog-button");                //获取当前button元素的blog-button属性，这里是绑定的logId
        let str2 = $(this).prev(".comment-words").val();       //获取当前评论内容
        let str3 = $(".glyphicon-user").text();                //获取username
        if (str2 !== "") {
            $.ajax({
                url: '/postComment',                           //网页根目录的相对路由
                type: 'post',                                  //采用post方法
                contentType: "application/json",
                async: false,
                data: JSON.stringify({                   //json标准化
                    logId: str1,
                    comment: str2
                }),
                complete: () => {                              //完成ajax请求后执行回调函数，在前端将评论展示，并将评论框置空
                    $(this).parents(".post-comment").before(
                        "<div class='comment'><span class=\"comment-user\">" + str3 + "：</span><span class=\"comment-content\">" + str2 + "</span></div>"
                    );
                    $(this).prev(".comment-words").val("");
                }

            })
        }
    })
})
