import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By
import java.text.NumberFormat as NumberFormat
import java.util.Locale as Locale

WebUI.callTestCase(findTestCase('Blocks/Reusable_TC/OpenBrowser'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Header/HeaderWithoutLogin/menuLogin'))

WebUI.callTestCase(findTestCase('Blocks/Register/Register'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(3)

WebUI.callTestCase(findTestCase('Blocks/Login/Login'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Object Repository/Home/btnAddtoCart'), 10)

if (WebUI.getText(findTestObject('Object Repository/Header/HeaderWithLogin/numberOfCarts')) != '0') {
    WebUI.click(findTestObject('Object Repository/Header/HeaderWithLogin/btnCart'))

    WebUI.click(findTestObject('Object Repository/Cart/btnClearcart'))

    WebUI.click(findTestObject('Object Repository/Header/titleBookCart'))
}

WebUI.callTestCase(findTestCase('Blocks/Cart/AddtoCart'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Cart/btnCheckOut'))

WebUI.delay(2)

WebUI.waitForElementVisible(findTestObject('Object Repository/Checkout/titleCheckOut'), 10)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Checkout/titleCheckOut')), 'Check Out', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Checkout/titleShippingaddress')), 'Shipping address', 
    false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Checkout/titleOrderSummary')), 'Order Summary', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Checkout/tableTitleTitle')), 'Title', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Checkout/tableTitlePrice')), 'Price', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Checkout/tableTitleQuantity')), 'Quantity', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Checkout/tableTitleTotal')), 'Total', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Checkout/tableTitleGrandTotal')), 'Grand Total', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Checkout/btnPlaceOrder')), 'Place Order', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Checkout/btnCancel')), 'Cancel', false)

List<WebElement> table = WebUI.findWebElements(findTestObject('Object Repository/Checkout/tableContent'), 10)

List<WebElement> bookContent = WebUI.findWebElements(findTestObject('Object Repository/Checkout/bookContent'), 10)

for (int i = 0; i < table.size(); i++) {
    List<WebElement> cols = (table[i]).findElements(By.tagName('td'))

    // Verified book title
    WebUI.verifyMatch((cols[0]).getText(), GlobalVariable.ListofBookTitles[i], false)

    // Verified quantity
    WebUI.verifyMatch((cols[1]).getText(), GlobalVariable.ListofBookQty[i], false)

    double qtyNumber = Double.parseDouble((cols[1]).getText())

    //Verified price
    WebUI.verifyMatch((cols[2]).getText(), GlobalVariable.ListofBookPrice[i], false)

    //Verified total
    double actualTotal = Double.parseDouble((cols[3]).getText().replaceAll('[^0-9.]', '').trim())

    String expectedTotalString = String.valueOf(GlobalVariable.ListofBookTotal[i]).replaceAll('[^0-9.]', '').trim()

    double expectedTotal = Double.parseDouble(expectedTotalString)

    WebUI.verifyMatch(String.format('%.2f', actualTotal), String.format('%.2f', expectedTotal), false)
}

NumberFormat formatIndia = NumberFormat.getNumberInstance(new Locale('en', 'IN'))

formatIndia.setMinimumFractionDigits(2)

formatIndia.setMaximumFractionDigits(2)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Checkout/grandTotal')), '₹' + formatIndia.format(GlobalVariable.ExpectedTotal), 
    false)

WebUI.setText(findTestObject('Object Repository/Checkout/inputName'), 'Dwi Ayu Daniar')

WebUI.setText(findTestObject('Object Repository/Checkout/inputAddressLine1'), 'Jl. Raya Baru Selatan No. 12')

WebUI.setText(findTestObject('Object Repository/Checkout/inputAddressLine2'), 'Kec. Matahari, Kel. Mawar')

WebUI.setText(findTestObject('Object Repository/Checkout/inputPincode'), '602345')

WebUI.setText(findTestObject('Object Repository/Checkout/inputState'), 'DKI Jakarta')

WebUI.click(findTestObject('Object Repository/Checkout/btnPlaceOrder'))

WebUI.delay(2)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/MyOrders/titleMyOrders')), 'My Orders', false)

WebUI.verifyElementVisible(findTestObject('Object Repository/MyOrders/inputSearch'))

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/MyOrders/tableTitleOrderId')), 'Order Id', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/MyOrders/tableTitleOrderedOn')), 'Ordered On', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/MyOrders/tableTitleOrderTotal')), 'Order Total', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/MyOrders/orderTotal')), '₹' + formatIndia.format(GlobalVariable.ExpectedTotal), 
    false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/MyOrders/labelItemsperpage')), 'Items per page:', false)

WebUI.click(findTestObject('Object Repository/MyOrders/orderId'))

List<WebElement> orderDetailsTable = WebUI.findWebElements(findTestObject('Object Repository/MyOrders/orderDetailsTable'), 10)

for (int y = 0; y < orderDetailsTable.size(); y++) {
	
    List<WebElement> cols = (orderDetailsTable[y]).findElements(By.tagName('td'))

    // Verified book title
    WebUI.verifyMatch((cols[0]).getText(), GlobalVariable.ListofBookTitles[y], false)

    // Verified quantity
    WebUI.verifyMatch((cols[1]).getText(), GlobalVariable.ListofBookQty[y], false)

    //Verified amount paid	
	String expectedFormatted = String.format("₹%.2f", Double.parseDouble(GlobalVariable.ListofBookTotal[y]))
	
    WebUI.verifyMatch((cols[2]).getText(), expectedFormatted, false)
	
}

WebUI.callTestCase(findTestCase('Blocks/Reusable_TC/CloseBrowser'), [:], FailureHandling.STOP_ON_FAILURE)

