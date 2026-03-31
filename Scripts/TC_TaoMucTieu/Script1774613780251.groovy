import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil

// ============================================================
// TC_TaoMucTieu  –  Flat Style (Manual View hiện đủ Object)
// Data-driven: 10 test case từ DataTest_TaoMucTieu
//   TC01 Pomodoro hợp lệ          → Success
//   TC02 Điểm số hợp lệ           → Success
//   TC03 Chương học hợp lệ        → Success
//   TC04 Bài kiểm tra hợp lệ      → Success
//   TC05 Bài học hợp lệ           → Success
//   TC06 Trống tiêu đề            → Fail
//   TC07 Trống số lượng           → Fail
//   TC08 Giá trị âm (-1)          → Fail
//   TC09 Ngày quá khứ             → Fail
//   TC10 Tạo tạm rồi Hủy         → Success
// ============================================================

// ----------------------------------------------------------------
// BƯỚC 1 – Mở trình duyệt
// ----------------------------------------------------------------
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('http://localhost:3000/')

// ----------------------------------------------------------------
// BƯỚC 2 – Đăng nhập nếu chưa có session
// ----------------------------------------------------------------
if (WebUI.waitForElementVisible(
        findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/button_TI  C TI KHON'),
        5, FailureHandling.OPTIONAL)) {

    WebUI.click(
        findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/button_TI  C TI KHON'))

    WebUI.setText(
        findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/input_ng nhp_email'),
        'themcao20@gmail.com')

    WebUI.setEncryptedText(
        findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/input_ng nhp_password'),
        'tE+PEiSUqqhzstxolVe06g==')

    WebUI.click(
        findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/button_ng nhp'))

    WebUI.waitForPageLoad(10)
}

// ----------------------------------------------------------------
// BƯỚC 3 – Điều hướng sang Mục tiêu học tập
// ----------------------------------------------------------------
WebUI.click(
    findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/span_Mc tiu hc tp'))
WebUI.waitForPageLoad(5)

// ----------------------------------------------------------------
// BƯỚC 4 – Log thông tin test case hiện tại
// ----------------------------------------------------------------
println ''
println '========================================================'
println " TieuDe       = ${TieuDe}"
println " LoaiMucTieu  = ${LoaiMucTieu}"
println " KyNang       = ${KyNang}"
println " ExpectedResult = ${ExpectedResult}"
println '========================================================'

// ================================================================
// TC10 – Tạo tạm rồi nhấn Hủy (kiểm tra modal đóng lại)
// ================================================================
if (TieuDe == 'Tạo tạm rồi hủy') {

    // Mở form
    WebUI.click(findTestObject('Object Repository/Page_LearningGoals/btn_Add_New_Goal'))
    WebUI.waitForElementVisible(findTestObject('Object Repository/Page_LearningGoals/input_Title'), 10)

    // Nhập tiêu đề tạm
    WebUI.setText(findTestObject('Object Repository/Page_LearningGoals/input_Title'), TieuDe)

    // Nhấn Hủy
    WebUI.click(findTestObject('Object Repository/Page_LearningGoals/btn_Cancel'))
    WebUI.delay(2)

    // Xác nhận modal đã đóng: nút Submit không còn visible
    boolean modalDaDong = !WebUI.waitForElementVisible(
        findTestObject('Object Repository/Page_LearningGoals/btn_Submit'),
        3, FailureHandling.OPTIONAL)

    if (modalDaDong) {
        println 'TC10 → PASS ✓  Modal đã đóng sau khi nhấn Hủy'
    } else {
        KeywordUtil.markFailed('TC10 FAIL: Modal vẫn hiển thị sau khi nhấn Hủy')
    }

// ================================================================
// TC01-TC09 – Tạo mục tiêu + Verify toast
// ================================================================
} else {

    // ------------------------------------------------------------
    // BƯỚC 5 – Mở form Thêm mục tiêu mới
    // ------------------------------------------------------------
    WebUI.click(findTestObject('Object Repository/Page_LearningGoals/btn_Add_New_Goal'))
    WebUI.waitForElementVisible(findTestObject('Object Repository/Page_LearningGoals/input_Title'), 10)

    // ------------------------------------------------------------
    // BƯỚC 6 – Nhập Tiêu đề
    // ------------------------------------------------------------
    WebUI.clearText(findTestObject('Object Repository/Page_LearningGoals/input_Title'))
    if (TieuDe != null && TieuDe != '') {
        WebUI.setText(findTestObject('Object Repository/Page_LearningGoals/input_Title'), TieuDe)
    }

    // ------------------------------------------------------------
    // BƯỚC 7 – Nhập Mô tả
    // ------------------------------------------------------------
    WebUI.clearText(findTestObject('Object Repository/Page_LearningGoals/input_Description'))
    if (MoTa != null && MoTa != '') {
        WebUI.setText(findTestObject('Object Repository/Page_LearningGoals/input_Description'), MoTa)
    }

    // ------------------------------------------------------------
    // BƯỚC 8 – Chọn Loại mục tiêu
    // ------------------------------------------------------------
    if (LoaiMucTieu != null && LoaiMucTieu != '') {
        WebUI.selectOptionByLabel(
            findTestObject('Object Repository/Page_LearningGoals/select_Type'),
            LoaiMucTieu, false)
    }

    // ------------------------------------------------------------
    // BƯỚC 9 – Chọn Kỹ năng
    // ------------------------------------------------------------
    if (KyNang != null && KyNang != '') {
        WebUI.selectOptionByLabel(
            findTestObject('Object Repository/Page_LearningGoals/select_Skill'),
            KyNang, false)
    }

    // ------------------------------------------------------------
    // BƯỚC 10 – Nhập trường Pomodoro
    //   (Số phiên / Làm việc / Nghỉ ngắn / Sau mỗi / Nghỉ dài)
    // ------------------------------------------------------------
    if (LoaiMucTieu == 'Pomodoro') {

        if (SoPhien != null && SoPhien != '') {
            WebUI.clearText(findTestObject('Object Repository/Page_LearningGoals/input_Target'))
            WebUI.setText(findTestObject('Object Repository/Page_LearningGoals/input_Target'), SoPhien)
        }

        if (Lamviec != null && Lamviec != '') {
            WebUI.clearText(findTestObject('Object Repository/Page_LearningGoals/input_WorkTime'))
            WebUI.setText(findTestObject('Object Repository/Page_LearningGoals/input_WorkTime'), Lamviec)
        }

        if (NghiNgan != null && NghiNgan != '') {
            WebUI.clearText(findTestObject('Object Repository/Page_LearningGoals/input_BreakTime'))
            WebUI.setText(findTestObject('Object Repository/Page_LearningGoals/input_BreakTime'), NghiNgan)
        }

        if (Saumoi != null && Saumoi != '') {
            WebUI.clearText(findTestObject('Object Repository/Page_LearningGoals/input_Interval'))
            WebUI.setText(findTestObject('Object Repository/Page_LearningGoals/input_Interval'), Saumoi)
        }

        if (Nghidai != null && Nghidai != '') {
            WebUI.clearText(findTestObject('Object Repository/Page_LearningGoals/input_LongBreak'))
            WebUI.setText(findTestObject('Object Repository/Page_LearningGoals/input_LongBreak'), Nghidai)
        }
    }

    // ------------------------------------------------------------
    // BƯỚC 11 – Nhập trường Điểm số
    //   (Điểm mục tiêu / Hạn hoàn thành)
    // ------------------------------------------------------------
    if (LoaiMucTieu == 'Điểm số') {

        if (Diemso != null && Diemso != '') {
            WebUI.clearText(findTestObject('Object Repository/Page_LearningGoals/input_Target'))
            WebUI.setText(findTestObject('Object Repository/Page_LearningGoals/input_Target'), Diemso)
        }

        if (Hanhoanthanh != null && Hanhoanthanh != '') {
            WebUI.clearText(findTestObject('Object Repository/Page_LearningGoals/input_Deadline'))
            WebUI.setText(findTestObject('Object Repository/Page_LearningGoals/input_Deadline'), Hanhoanthanh)
        }
    }

    // ------------------------------------------------------------
    // BƯỚC 12 – Nhập trường Chương học
    //   (Số chương / Hạn hoàn thành)
    // ------------------------------------------------------------
    if (LoaiMucTieu == 'Chương học') {

        if (Sochuong != null && Sochuong != '') {
            WebUI.clearText(findTestObject('Object Repository/Page_LearningGoals/input_Target'))
            WebUI.setText(findTestObject('Object Repository/Page_LearningGoals/input_Target'), Sochuong)
        }

        if (Hanhoanthanh != null && Hanhoanthanh != '') {
            WebUI.clearText(findTestObject('Object Repository/Page_LearningGoals/input_Deadline'))
            WebUI.setText(findTestObject('Object Repository/Page_LearningGoals/input_Deadline'), Hanhoanthanh)
        }
    }

    // ------------------------------------------------------------
    // BƯỚC 13 – Nhập trường Bài kiểm tra
    //   (Số bài kiểm tra / Hạn hoàn thành)
    // ------------------------------------------------------------
    if (LoaiMucTieu == 'Bài kiểm tra') {

        if (Sobaikt != null && Sobaikt != '') {
            WebUI.clearText(findTestObject('Object Repository/Page_LearningGoals/input_Target'))
            WebUI.setText(findTestObject('Object Repository/Page_LearningGoals/input_Target'), Sobaikt)
        }

        if (Hanhoanthanh != null && Hanhoanthanh != '') {
            WebUI.clearText(findTestObject('Object Repository/Page_LearningGoals/input_Deadline'))
            WebUI.setText(findTestObject('Object Repository/Page_LearningGoals/input_Deadline'), Hanhoanthanh)
        }
    }

    // ------------------------------------------------------------
    // BƯỚC 14 – Nhập trường Bài học
    //   (Số bài học = Sobaikt ưu tiên, fallback Sochuong / Hạn hoàn thành)
    // ------------------------------------------------------------
    if (LoaiMucTieu == 'Bài học') {

        String valBaiHoc = (Sobaikt != null && Sobaikt != '') ? Sobaikt
                         : (Sochuong != null && Sochuong != '') ? Sochuong : ''

        if (valBaiHoc != '') {
            WebUI.clearText(findTestObject('Object Repository/Page_LearningGoals/input_Target'))
            WebUI.setText(findTestObject('Object Repository/Page_LearningGoals/input_Target'), valBaiHoc)
        }

        if (Hanhoanthanh != null && Hanhoanthanh != '') {
            WebUI.clearText(findTestObject('Object Repository/Page_LearningGoals/input_Deadline'))
            WebUI.setText(findTestObject('Object Repository/Page_LearningGoals/input_Deadline'), Hanhoanthanh)
        }
    }

    // ------------------------------------------------------------
    // BƯỚC 15 – Submit form Tạo mục tiêu
    // ------------------------------------------------------------
    WebUI.click(findTestObject('Object Repository/Page_LearningGoals/btn_Submit'))

    // ------------------------------------------------------------
    // BƯỚC 16 – Chờ và đọc Toast notification
    // ------------------------------------------------------------
    String toastText = ''
    if (WebUI.waitForElementVisible(
            findTestObject('Object Repository/Page_LearningGoals/msg_Toast'),
            8, FailureHandling.OPTIONAL)) {

        toastText = WebUI.getText(
            findTestObject('Object Repository/Page_LearningGoals/msg_Toast'),
            FailureHandling.OPTIONAL) ?: ''
    }

    println "Toast: [${toastText}]  |  Expected: ${ExpectedResult}"

    // ------------------------------------------------------------
    // BƯỚC 17 – Verify kết quả theo ExpectedResult
    //   Success: toast chứa "thành công" hoặc "Đã thêm"
    //   Fail   : toast chứa "Lỗi" / "lỗi" / "Vui lòng" hoặc rỗng
    // ------------------------------------------------------------
    boolean isSuccess = toastText.toLowerCase().contains('thành công') || toastText.contains('Đã thêm')
    boolean isFail    = toastText.contains('Lỗi') || toastText.contains('lỗi') ||
                        toastText.contains('Vui lòng') || toastText.trim() == ''

    if (ExpectedResult == 'Success') {
        if (isSuccess) {
            println "→ PASS ✓  Tạo mục tiêu thành công"
        } else {
            KeywordUtil.markFailed("FAIL: ExpectedResult=Success nhưng toast=[${toastText}]")
        }
    } else {
        if (isFail) {
            println "→ PASS ✓  Hệ thống từ chối đúng"
        } else {
            KeywordUtil.markFailed("FAIL: ExpectedResult=Fail nhưng toast=[${toastText}]")
        }
    }

    WebUI.delay(2)
}

// ----------------------------------------------------------------
// BƯỚC 18 – Kết thúc
// ----------------------------------------------------------------
WebUI.delay(1)
