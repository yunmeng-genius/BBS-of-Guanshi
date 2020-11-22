$(document).ready(function () {
    let commentUser = "";
    let commentContent = "";
    $(".comment-form").submit(function () {
        let str1 = $(".log-id").text();
        let str2 = $(".comment-words").val();
        $.ajax({
            url: 'http://localhost:8088/postComment',
            type: 'post',
            data: JSON.stringify({
                logId: str1,
                comment: str2
            }),
            dataType: JSON,
            contentType:"application/json",
            success: function (data) {
                data=JSON.stringify(data);
                if (data.hasOwnProperty("username")) {
                    // (".new-comment-user").val(data["username"]);
                    commentUser = data["username"];
                } else if (data.hasOwnProperty("comment")) {
                    // $(".new-comment-content").val(data["comment"]);
                    commentContent = data["comment"];
                }
                $(".post-comment").before(function () {
                    return "<div class='new-comment'><span>" + commentUser + "</span><span>" + commentContent + "</span></div>"
                })
            },
            error: function () {
                alert("发生了一点小故障哦，请稍等");
            }
        })
    })

})