$(document).ready(function () {
    $(".blog-button").click(function () {
        let str1 = $(this).attr("blog-button");
        let str2 = $(this).prev(".comment-words").val();
        let str3 = $(".glyphicon-user").text();
        if (str2 !== "") {
            $.ajax({
                url: '/postComment',
                type: 'post',
                contentType: "application/json",
                async: false,
                data: JSON.stringify({
                    logId: str1,
                    comment: str2
                }),
                complete: () => {
                    $(this).parents(".post-comment").before(
                        "<div class='comment'><span class=\"comment-user\">" + str3 + "：</span><span class=\"comment-content\">" + str2 + "</span></div>"
                    );
                    $(this).prev(".comment-words").val("");
                }

            })
        }
    })
})
