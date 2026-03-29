import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Chắp thêm phần mở trình duyệt để tránh lỗi BrowserNotOpenedException
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('http://localhost:3000/')

// Đăng nhập (Dùng thông tin từ tài khoản của bạn)
if (WebUI.waitForElementVisible(findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/button_TI  C TI KHON'), 5, FailureHandling.OPTIONAL)) {
    WebUI.click(findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/button_TI  C TI KHON'))
    WebUI.setText(findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/input_ng nhp_email'), 'themcao20@gmail.com')
    WebUI.setEncryptedText(findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/input_ng nhp_password'), 'tE+PEiSUqqhzstxolVe06g==')
    WebUI.click(findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/button_ng nhp'))
}

// Chuyển sang mục Mục tiêu học tập
WebUI.click(findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/span_Mc tiu hc tp'))
WebUI.waitForPageLoad(5)

// Repository tôi đã tạo giúp bạn (Page_LearningGoals)
String rP = 'Object Repository/Page_LearningGoals/'

// Nhấn nút thêm mới
WebUI.click(findTestObject(rP + 'btn_Add_New_Goal'))
WebUI.waitForElementVisible(findTestObject(rP + 'input_Title'), 5)

// Gán chính xác 13 biến cài đặt từ file dữ liệu của bạn
if (TieuDe != "") WebUI.setText(findTestObject(rP + 'input_Title'), TieuDe)
if (MoTa != "") WebUI.setText(findTestObject(rP + 'input_Description'), MoTa)

WebUI.selectOptionByLabel(findTestObject(rP + 'select_Type'), LoaiMucTieu, false)
WebUI.selectOptionByLabel(findTestObject(rP + 'select_Skill'), KyNang, false)

// Xử lý thông số theo từng loại mục tiêu đã chọn
switch (LoaiMucTieu) {
    case 'Pomodoro':
        if (SoPhien != "") WebUI.setText(findTestObject(rP + 'input_Target'), SoPhien)
        if (Lamviec != "") WebUI.setText(findTestObject(rP + 'input_WorkTime'), Lamviec)
        if (NghiNgan != "") WebUI.setText(findTestObject(rP + 'input_BreakTime'), NghiNgan)
        if (Saumoi != "") WebUI.setText(findTestObject(rP + 'input_Interval'), Saumoi)
        if (Nghidai != "") WebUI.setText(findTestObject(rP + 'input_LongBreak'), Nghidai)
        break
    case 'Điểm số':
        if (Diemso != "") WebUI.setText(findTestObject(rP + 'input_Target'), Diemso)
        if (Hanhoanthanh != "") WebUI.setText(findTestObject(rP + 'input_Deadline'), Hanhoanthanh)
        break
    case 'Chương học':
        if (Sochuong != "") WebUI.setText(findTestObject(rP + 'input_Target'), Sochuong)
        if (Hanhoanthanh != "") WebUI.setText(findTestObject(rP + 'input_Deadline'), Hanhoanthanh)
        break
    case 'Bài kiểm tra':
        if (Sobaikt != "") WebUI.setText(findTestObject(rP + 'input_Target'), Sobaikt)
        if (Hanhoanthanh != "") WebUI.setText(findTestObject(rP + 'input_Deadline'), Hanhoanthanh)
        break
    case 'Bài học':
        // Dùng số chương hoặc số bài kiểm tra cho mục tiêu số bài học
        String val = (Sochuong != "") ? Sochuong : Sobaikt
        if (val != "") WebUI.setText(findTestObject(rP + 'input_Target'), val)
        if (Hanhoanthanh != "") WebUI.setText(findTestObject(rP + 'input_Deadline'), Hanhoanthanh)
        break
}

// Lưu mục tiêu
WebUI.click(findTestObject(rP + 'btn_Submit'))

// Đợi và in kết quả thông báo
if (WebUI.waitForElementVisible(findTestObject(rP + 'msg_Toast'), 5, FailureHandling.OPTIONAL)) {
    println "Kết quả: " + WebUI.getText(findTestObject(rP + 'msg_Toast'))
}

WebUI.delay(2)
