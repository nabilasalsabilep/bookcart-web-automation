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

WebUI.callTestCase(findTestCase('Blocks/Reusable_TC/OpenBrowser'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(1)

WebUI.mouseOver(findTestObject('Object Repository/Home/bookImage'))

GlobalVariable.BookTitle = WebUI.getText(findTestObject('Object Repository/Home/bookTitleWhenHover'))

GlobalVariable.BookPrice = WebUI.getText(findTestObject('Object Repository/Home/bookPrice'))

WebUI.click(findTestObject('Object Repository/Home/bookImage'))

WebUI.delay(1)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/BookDetails/titleBookDetails')), 'Book Details', false)

WebUI.verifyElementVisible(findTestObject('Object Repository/BookDetails/imageBookDetails'))

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/BookDetails/labelTitle')), 'Title', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/BookDetails/bookTitle')), GlobalVariable.BookTitle, false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/BookDetails/labelAuthor')), 'Author', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/BookDetails/labelCategory')), 'Category', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/BookDetails/labelPrice')), 'Price', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/BookDetails/bookPrice')), GlobalVariable.BookPrice, false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/BookDetails/btnAddtoCart')), 'Add to Cart', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/BookDetails/titleSimilarBooks')), 'Similar Books', false)

WebUI.verifyElementVisible(findTestObject('Object Repository/BookDetails/imageSimilarBooks'))

WebUI.verifyElementVisible(findTestObject('Object Repository/BookDetails/similarBooksTitle'))

WebUI.verifyElementVisible(findTestObject('Object Repository/BookDetails/similarBooksPrice'))

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/BookDetails/similarBooksButtonAddtoCart')), 'Add to Cart', false)

url = WebUI.getUrl()

WebUI.navigateToUrl(url)

WebUI.verifyElementText(findTestObject('Object Repository/BookDetails/titleNoBooksFound'), 'No books found.')

WebUI.verifyElementText(findTestObject('Object Repository/BookDetails/btnBacktoHome'), 'Back to Home')

WebUI.click(findTestObject('Object Repository/BookDetails/btnBacktoHome'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Home/bookImage'), 10)

WebUI.callTestCase(findTestCase('Blocks/Reusable_TC/CloseBrowser'), [:], FailureHandling.STOP_ON_FAILURE)

