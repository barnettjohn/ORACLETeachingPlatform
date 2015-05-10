 $("#submit_question").click(function() {
    var arry = $("input:checked");
    var qid ;
    var qname ;
    for(var i = 0;i<arry.length;i++){
        var a = arry[i];
        qid = a.id;
        qname = a.name;
        if(qname === "title-table-checkbox"){
            
        }else{
            $option = $(" <option></option>");
            $option.attr("value", qid);
            $option.text(qname);
            var b = true;
            $("#test_question2 option").each(function (){
                if($(this).val() == qid){
                    b=false;
                }
            });
            if(b===true){
                $("#test_question2").append($option);
            }
            $(a).closest('tr').remove();
        }
    }
    
});