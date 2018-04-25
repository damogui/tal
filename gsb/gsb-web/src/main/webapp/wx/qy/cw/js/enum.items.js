var formTypeDict = new System.Dictionary();
formTypeDict.add(3, "借款单");
formTypeDict.add(4, "报销单");
formTypeDict.add(5, "付款单");

var paymentMethodDict = new System.Dictionary();
paymentMethodDict.add(1, "现金");
paymentMethodDict.add(2, "转账");
paymentMethodDict.add(3, "支票");


var loanBillTypeDict = new System.Dictionary();
loanBillTypeDict.add(1, "日常借款");
loanBillTypeDict.add(2, "招待费借款");
loanBillTypeDict.add(3, "差旅费借款");
loanBillTypeDict.add(4, "其他借款");

var auditStatusDict = new System.Dictionary();
auditStatusDict.add(1,"待审核");
auditStatusDict.add(2,"通过");
auditStatusDict.add(3,"驳回");

var invoiceTypeDict =  new System.Dictionary();
invoiceTypeDict.add(1,"普通发票");
invoiceTypeDict.add(2,"增值税发票");


var ExpenseTypeDict = new System.Dictionary();
ExpenseTypeDict.add(1, "市场费报销");
ExpenseTypeDict.add(2, "招待费报销");
ExpenseTypeDict.add(3, "差旅费报销");

var TaxRateTypeDict = new System.Dictionary();
TaxRateTypeDict.add(3, "3%");
TaxRateTypeDict.add(6, "6%");
TaxRateTypeDict.add(16, "16%");

var SubsidyTypeDict = new System.Dictionary();
SubsidyTypeDict.add(1, "出差补助");
SubsidyTypeDict.add(2, "交通补助");
SubsidyTypeDict.add(3, "餐补");
SubsidyTypeDict.add(4, "话费补助");

