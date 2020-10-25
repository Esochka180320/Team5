$(document).ready(function(){

    $("#ldep").val(5);
    var sum =100;
    $.ajax({
        type: "GET",
        contentType : 'application/json; charset=utf-8',
        dataType : "json",
        url: "/main-page",

        success :function(response){
            $('#balanc').text(response.balans +"$")
            $('#acount').text(response.userName)
            $('#game').html("<option>"+response.gamesName[0]+"</option><option>"+response.gamesName[1]+"</option><option>"+response.gamesName[2]+"</option><option>"+response.gamesName[3]+"</option><option>"+response.gamesName[4]+"</option><option>"+response.gamesName[5]+"</option>")
            console.log(response.gamesName[0]);



        },
        error:function(response,textStatus) {
            alert("I am in error ajax");



        }
    });


var ran = Math.floor(Math.random() * 6) + 1;
 sum =100;
    $("#dep").click(function() {
        while (l>=0){

            var l =  $("#ldep").val();
            l--;
            if ($("#dep").val()==ran){

                $.ajax({
                    type: "POST",
                    contentType : 'application/json; charset=utf-8',
                    dataType : "json",
                    url: '/get-deposite/'+sum,

                    success :function(response){

                       alert("add"+sum)

                    },
                    error:function(response) {
                        alert("smt wrong "+ response);

                    }
                });
            }
            sum=sum-25;
        }
        $("#dep").hidden = true;


    });
});
