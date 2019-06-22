console.log("Hello");
$(document).ready(function(){
    $.ajax({
        type: "GET",
        url:"/xml/abcd.xml",
        dataType: "xml",
        success: function(xml){
            console.log(xml);
            var $xml = $(xml);
            console.log($xml);
            var $person = $xml.find("cities");
            console.log($person);
            var $name = $person.find('city');
            $name.each(function () {
                $("#select").append("<option>"+$(this).find('name').text()+"</option>");
            });
        },
        error:function (err) {
            console.log(err);
        }
    });
});
