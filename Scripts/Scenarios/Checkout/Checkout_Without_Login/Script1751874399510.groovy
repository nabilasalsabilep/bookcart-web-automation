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

WebUI.callTestCase(findTestCase('Blocks/Reusable_TC/OpenBrowser'), [:], FailureHandling.STOP_ON_FAILURE)

GlobalVariable.ExpectedTotal = 0.0

WebUI.click(findTestObject('Object Repository/Header/HeaderWithoutLogin/btnCart'))

if (WebUI.waitForElementVisible(findTestObject('Object Repository/Cart/tableTitleImage'), 5)) {
    WebUI.click(findTestObject('Object Repository/Cart/btnClearcart'))
}

WebUI.click(findTestObject('Object Repository/Header/titleBookCart'))

WebUI.delay(1)

WebUI.mouseOver(findTestObject('Object Repository/Home/bookImage'))

(GlobalVariable.ListofBookTitles[0]) = WebUI.getText(findTestObject('Object Repository/Home/bookTitleWhenHover'))

(GlobalVariable.ListofBookPrice[0]) = WebUI.getText(findTestObject('Object Repository/Home/bookPrice'))

WebUI.click(findTestObject('Object Repository/Home/btnAddtoCart'))

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Header/HeaderWithoutLogin/numberOfCarts')), '1', false)

WebUI.waitForElementVisible(findTestObject('Object Repository/Home/bookTitle'), 10)

WebUI.click(findTestObject('Object Repository/Header/HeaderWithoutLogin/btnCart'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Cart/titleShoppingcart'), 10)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Cart/titleShoppingcart')), 'Shopping cart', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Cart/btnClearcart')), 'Clear cart', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Cart/tableTitleImage')), 'Image', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Cart/tableTitleTitle')), 'Title', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Cart/tableTitlePrice')), 'Price', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Cart/tableTitleQuantity')), 'Quantity', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Cart/tableTitleTotal')), 'Total', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Cart/tableTitleAction')), 'Action', false)

List<WebElement> tableContent = WebUI.findWebElements(findTestObject('Object Repository/Cart/tableSize'), 10)

List<WebElement> bookImageListInTable = WebUI.findWebElements(findTestObject('Object Repository/Cart/listOfBookImage'), 
    10)

List<WebElement> bookTitleListInTable = WebUI.findWebElements(findTestObject('Object Repository/Cart/listOfBookTitles'), 
    10)

List<WebElement> bookPriceListInTable = WebUI.findWebElements(findTestObject('Object Repository/Cart/listOfBookPrice'), 
    10)

List<WebElement> bookQtyListInTable = WebUI.findWebElements(findTestObject('Object Repository/Cart/listOfBookQuantity'), 
    10)

List<WebElement> bookTotalListInTable = WebUI.findWebElements(findTestObject('Object Repository/Cart/listOfBookTotal'), 
    10)

List<WebElement> deleteItemInTable = WebUI.findWebElements(findTestObject('Object Repository/Cart/listOfDeleteItemButton'), 
    10)

List<WebElement> addQuantityButtonInTable = WebUI.findWebElements(findTestObject('Object Repository/Cart/addQuantityButton'), 
    10)

for (int i = 0; i < tableContent.size(); i++) {
    assert (bookImageListInTable[i]).isDisplayed()

    String actualBookTitle = bookTitleListInTable.get(i).getText()

    String actualBookPrice = bookPriceListInTable.get(i).getText()

    WebUI.verifyMatch(actualBookTitle, GlobalVariable.ListofBookTitles[i], false)

    WebUI.verifyMatch(actualBookPrice, GlobalVariable.ListofBookPrice[i], false)

    WebUI.verifyMatch(bookQtyListInTable.get(i).getText(), '1', false)

    assert (deleteItemInTable[i]).isDisplayed()

    String priceText = bookPriceListInTable.get(i).getText().replaceAll('[^0-9.]', '').trim()

    String totalText = bookPriceListInTable.get(i).getText().replaceAll('[^0-9.]', '').trim()

    (GlobalVariable.ListofBookTotal[i]) = totalText

    // Parse to double for calculations
    double qtyNumber = Double.parseDouble(bookQtyListInTable.get(i).getText())

    double convertPriceToDouble = Double.parseDouble(priceText)

    double actualTotal = Double.parseDouble(totalText)

    double expectedTotal = convertPriceToDouble * qtyNumber

    // Validate if the expected total matches the actual total
    WebUI.verifyMatch(String.format('%.2f', actualTotal), String.format('%.2f', expectedTotal), false)
    
    // Add the actual total to expectedtotal
    GlobalVariable.ExpectedTotal += expectedTotal
}

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Cart/labelCartTotal')), 'Cart Total:', false)

String total = WebUI.getText(findTestObject('Object Repository/Cart/cartTotal')).replaceAll('[^0-9.]', '').trim()

double actualTotal = Double.parseDouble(total)

// Validate total
WebUI.verifyMatch(String.format('%.2f', actualTotal).replaceAll('[^\\d.]', ''), String.format('%.2f', GlobalVariable.ExpectedTotal), false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Cart/btnCheckOut')), 'CheckOut', false)

WebUI.click(findTestObject('Object Repository/Cart/btnCheckOut'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Login/titleLogin'), 10)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Login/titleLogin')), 'Login', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Login/titleNewUser')), 'New User?', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Login/btnRegister')), 'Register', false)

WebUI.callTestCase(findTestCase('Blocks/Reusable_TC/CloseBrowser'), [:], FailureHandling.STOP_ON_FAILURE)

