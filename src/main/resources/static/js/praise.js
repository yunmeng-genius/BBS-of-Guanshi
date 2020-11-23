$(document).ready(function () {
    $(".praise-action").click(function () {
        let praiseOne = $(".glyphicon-user").text();
        let logId = $(this).attr("praise-button");
        $.ajax({
            url: "/praise",
            type: 'post',
            contentType: "application/json",
            async: false,
            data: JSON.stringify({
                logId: logId
            }),
            success: () => {
                $(this).after(
                    "<span class='praise-user'>" + praiseOne + "</span>"
                )
            },
            error: () => {
                $(this).after("<span class='praise-user'>" + praiseOne + "、</span>"
                )
            }
        })
    })
})