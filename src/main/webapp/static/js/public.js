/**
 * 定义一个全局表单提交请求函数
 * @param form
 * @param action
 * @param target
 * @returns
 */
function actSubmit(oform, action, target) {
    if (target) oform.attr("target", target);
    if (action) oform.attr("action",action);
    oform.submit();
}