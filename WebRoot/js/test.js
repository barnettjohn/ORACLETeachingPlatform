
$(document).ready(function(){
    $("#submit_question").click(function() {
        
        //alert("data");
        
        $.post("GetTestQuestionAjaxServlet", $("#questionlist").serialize(), function(data, textStatus) {
                    
                    alert(data);
                    //$("#test_validate select").html(data)
                
                    var obj = eval("(" + data + ")");
                    for (var i = 0; i < obj.length; i++) {
                        var qid = obj[i].qid;
                        var qname = obj[i].qname;
                        $option = $(" <option></option>");
                        $option.attr("value", qid);
                        $option.text(qname);
                        $("#test_question2").append($option);
                        //$("#detail").attr("value", qname);
                    }
                    // $("#content-header").text(data);
                    
                });
        
   // $("testname").attr("value","aaaaaa");
    });
 });
