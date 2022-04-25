
from selenium.webdriver.remote.webelement import WebElement
from selenium.webdriver.chrome.webdriver import WebDriver


def next_element(driver: WebDriver, elem: WebElement) -> WebElement:
    next_sibiling = driver.execute_script("""
        return arguments[0].nextElementSibling
        """, elem)
    return next_sibiling
