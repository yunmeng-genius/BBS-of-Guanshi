$(document).ready(function () {
    $(".praise-action").click(function () {
            let praiseOne = $(".glyphicon-user").text();
            let logId = $(this).attr("praise-button");
            let flag = true;
            $(this).nextUntil(".help-praise").each((index, element) => {
                if ($(element).text() === praiseOne) {
                    flag = false;
                }
            })
            $(this).nextAll(".praise-user").find(".old-praise-user").each((each,element)=>{
                if ($(element).text() === praiseOne) {
                    flag = false;
                }
            })
            if (flag) {
                $.ajax({
                    url: "/praise",
                    type: 'post',
                    contentType: "application/json",
                    async: false,
                    data: JSON.stringify({
                        logId: logId
                    }),
                    complete: () => {
                        $(this).after(
                            "<span class='praise-user'>" + praiseOne + "</span>"
                        )
                    },
                })
            }
        }
    )
})