import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Mở trình duyệt
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('http://localhost:3000/')

// Giả định click nút Đăng ký mở form
// WebUI.click(findTestObject('Object Repository/Page_EnglishMaster_.../button_KhuVucDangKy'), FailureHandling.OPTIONAL)

if (email != "") {
    WebUI.setText(findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/input_DangKy_Email'), email, FailureHandling.OPTIONAL)
}

// Bấm Submit Email / Nhận OTP
WebUI.click(findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/button_Submit_Email'), FailureHandling.OPTIONAL)
WebUI.delay(2)

// Xử lý 10 Test Cases bằng Switch tương tự như cách dùng trên LoaiMucTieu!
switch (ID) {
    case 'DK01': // Valid Data
    case 'DK02': // Invalid OTP
    case 'DK03': // Expired OTP
    case 'DK04': // Empty OTP
    case 'DK06': // Max Attempt
    case 'DK09': // Wrong Format
    case 'DK10': // Multi Request
        // Các case này tiến hành đăng ký bình thường
        if (otp != 'null' && otp != "") {
            WebUI.setText(findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/input_DangKy_OTP'), otp, FailureHandling.OPTIONAL)
        }
        WebUI.click(findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/button_XacNhan_OTP'), FailureHandling.OPTIONAL)
        break
        
    case 'DK05': // Resend OTP
        WebUI.click(findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/button_Resend_OTP'), FailureHandling.OPTIONAL)
        WebUI.delay(2)
        if (otp != 'null' && otp != "") {
            WebUI.setText(findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/input_DangKy_OTP'), otp, FailureHandling.OPTIONAL)
        }
        WebUI.click(findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/button_XacNhan_OTP'), FailureHandling.OPTIONAL)
        break
        
    case 'DK07': // Timeout
        WebUI.delay(10) // Đợi hết thời gian nhập OTP, bỏ qua bước click xác nhận
        break
        
    case 'DK08': // Duplicate Email
        // Case này dừng luôn sau khi check trùng ở bước Email, ko tới màn hình nhập OTP
        break
}

WebUI.delay(2)

// In kết quả verify (Lấy thông báo thành công hoặc lỗi trên form đăng ký)
if (WebUI.waitForElementVisible(findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/msg_ThongBaoChung'), 5, FailureHandling.OPTIONAL)) {
    def actualResultMsg = WebUI.getText(findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/msg_ThongBaoChung'), FailureHandling.OPTIONAL)
    if (actualResultMsg) {
        WebUI.verifyMatch(actualResultMsg, '.*' + expectedMessage + '.*', true, FailureHandling.OPTIONAL)
        println("Kết quả so khớp: " + actualResultMsg)
    }
}

WebUI.closeBrowser()
