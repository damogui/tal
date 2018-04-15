System.Declare("com.gongsibao.igirl.tm.web");
com.gongsibao.igirl.tm.web.ChangeTradeMarkPart = org.netsharp.panda.commerce.FormPart.Extends({

    function IsVain(s)
{
    if ($.trim(s) == '') {
        return true;
    } else {
        return false;
    }
}
)
;