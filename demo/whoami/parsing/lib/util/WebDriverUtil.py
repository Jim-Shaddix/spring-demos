
from typing import List
from selenium.webdriver.remote.webelement import WebElement
from selenium.webdriver.chrome.webdriver import WebDriver
from selenium.webdriver.common.by import By


def next_element(driver: WebDriver, elem: WebElement) -> WebElement:
    next_sibiling = driver.execute_script("""
        return arguments[0].nextElementSibling
        """, elem)
    return next_sibiling


def css_select(element, css_selector) -> WebElement:
    return element.find_element(by=By.CSS_SELECTOR, value=css_selector)


def css_select_all(element, css_selector) -> List[WebElement]:
    return element.find_elements(by=By.CSS_SELECTOR, value=css_selector)
