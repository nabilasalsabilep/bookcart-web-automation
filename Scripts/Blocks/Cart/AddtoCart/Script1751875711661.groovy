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

WebUI.delay(1)

WebUI.mouseOver(findTestObject('Object Repository/Home/bookImage'))

(GlobalVariable.ListofBookTitles[0]) = WebUI.getText(findTestObject('Object Repository/Home/bookTitleWhenHover'))

(GlobalVariable.ListofBookPrice[0]) = WebUI.getText(findTestObject('Object Repository/Home/bookPrice'))

WebUI.click(findTestObject('Object Repository/Home/btnAddtoCart'))

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Header/HeaderWithLogin/numberOfCarts')), '1', false)

WebUI.click(findTestObject('Object Repository/Home/bookImage'))

WebUI.scrollToElement(findTestObject('Object Repository/BookDetails/similarBooksTitle'), 10)

WebUI.delay(1)

WebUI.mouseOver(findTestObject('Object Repository/BookDetails/imageSimilarBooks'))

(GlobalVariable.ListofBookTitles[1]) = WebUI.getText(findTestObject('Object Repository/BookDetails/similarBooksTitleWhenHover'))

(GlobalVariable.ListofBookPrice[1]) = WebUI.getText(findTestObject('Object Repository/BookDetails/similarBooksPrice'))

WebUI.click(findTestObject('Object Repository/BookDetails/similarBooksButtonAddtoCart'))

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Header/HeaderWithLogin/numberOfCarts')), '2', false)

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/Header/HeaderWithLogin/btnCart'))

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

for (int i = 0; i < tableContent.size(); i++) {
	// Re-fetch element setiap loop untuk menghindari stale reference
	List<WebElement> bookImageListInTable = WebUI.findWebElements(findTestObject('Object Repository/Cart/listOfBookImage'), 10)
	List<WebElement> bookTitleListInTable = WebUI.findWebElements(findTestObject('Object Repository/Cart/listOfBookTitles'), 10)
	List<WebElement> bookPriceListInTable = WebUI.findWebElements(findTestObject('Object Repository/Cart/listOfBookPrice'), 10)
	List<WebElement> bookQtyListInTable = WebUI.findWebElements(findTestObject('Object Repository/Cart/listOfBookQuantity'), 10)
	List<WebElement> bookTotalListInTable = WebUI.findWebElements(findTestObject('Object Repository/Cart/listOfBookTotal'), 10)
	List<WebElement> deleteItemInTable = WebUI.findWebElements(findTestObject('Object Repository/Cart/listOfDeleteItemButton'), 10)
	List<WebElement> addQuantityButtonInTable = WebUI.findWebElements(findTestObject('Object Repository/Cart/addQuantityButton'), 10)

	assert bookImageListInTable[i].isDisplayed()

	String actualBookTitle = bookTitleListInTable[i].getText()
	String actualBookPrice = bookPriceListInTable[i].getText()
	WebUI.verifyMatch(actualBookTitle, GlobalVariable.ListofBookTitles[i], false)
	WebUI.verifyMatch(actualBookPrice, GlobalVariable.ListofBookPrice[i], false)
	WebUI.verifyMatch(bookQtyListInTable[i].getText(), '1', false)
	assert deleteItemInTable[i].isDisplayed()

	String priceText = actualBookPrice.replaceAll('[^0-9.]', '').trim()
	String totalText = bookTotalListInTable[i].getText().replaceAll('[^0-9.]', '').trim()
	GlobalVariable.ListofBookTotal[i] = totalText

	double qtyNumber = Double.parseDouble(bookQtyListInTable[i].getText())
	double convertPriceToDouble = Double.parseDouble(priceText)
	double actualTotal = Double.parseDouble(totalText)
	double expectedTotal = convertPriceToDouble * qtyNumber

	WebUI.verifyMatch(String.format('%.2f', actualTotal), String.format('%.2f', expectedTotal), false)
	GlobalVariable.ListofBookQty[i] = bookQtyListInTable[i].getText()

	if (i == 0) {
		// Add quantity of first book item
		addQuantityButtonInTable[0].click()
		WebUI.delay(1)

		// Re-fetch specific elements for updated values
		WebElement updatedQtyElement = WebUI.findWebElement(findTestObject('Object Repository/Cart/listOfBookQuantity'), 10)
		WebElement updatedTotalElement = WebUI.findWebElement(findTestObject('Object Repository/Cart/listOfBookTotal'), 10)
		WebElement updatedPriceElement = WebUI.findWebElement(findTestObject('Object Repository/Cart/listOfBookPrice'), 10)

		String updatedQtyText = updatedQtyElement.getText().trim()
		WebUI.verifyMatch(updatedQtyText, '2', false)

		double qtyFirstItem = Double.parseDouble(updatedQtyText)
		GlobalVariable.ListofBookQty[i] = updatedQtyText

		double updatedPrice = Double.parseDouble(updatedPriceElement.getText().replaceAll('[^0-9.]', '').trim())
		double updatedActualTotal = Double.parseDouble(updatedTotalElement.getText().replaceAll('[^0-9.]', '').trim())
		double updatedExpectedTotal = updatedPrice * qtyFirstItem

		WebUI.verifyMatch(String.format('%.2f', updatedActualTotal), String.format('%.2f', updatedExpectedTotal), false)
		
		GlobalVariable.ListofBookTotal[i] = String.format('%.2f', updatedActualTotal)
		
		GlobalVariable.ExpectedTotal += updatedExpectedTotal
	} else {
		GlobalVariable.ExpectedTotal += expectedTotal
	}
}

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Cart/labelCartTotal')), 'Cart Total:', false)

String total = WebUI.getText(findTestObject('Object Repository/Cart/cartTotal')).replaceAll('[^0-9.]', '').trim()

double actualTotal = Double.parseDouble(total)
	
// Validate total
WebUI.verifyMatch(String.format('%.2f', actualTotal), String.format(
		'%.2f', GlobalVariable.ExpectedTotal), false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Cart/btnCheckOut')), 'CheckOut', false)