import json
import sys

from selenium.webdriver.common.by import By
from selenium.common.exceptions import NoSuchElementException
from selenium.webdriver.remote.webelement import WebElement
from selenium.webdriver.chrome.webdriver import WebDriver
from typing import List, Dict
from config import Config


class ResponseCodeSpec:

    def __init__(self, rc_type, rc_name, rc_desc, link):
        self.rc_type = rc_type
        self.rc_name = rc_name
        self.rc_desc = rc_desc
        self.link = link

    def to_map(self) -> Dict[str, str]:
        return {
            "type": self.rc_type,
            "name": self.rc_name,
            "description": self.rc_desc,
            "link": self.link
        }

    def __str__(self):
        return f"ResponseCodeSpec=[type:{self.rc_type}, name:{self.rc_name}, link:{self.link}, description:{self.rc_desc}]"

    def __repr__(self):
        return self.__str__()


def next_element(driver: WebDriver, elem: WebElement) -> WebElement:
    next_sibiling = driver.execute_script("""
        return arguments[0].nextElementSibling
        """, elem)
    return next_sibiling


def parse_content(driver) -> List[ResponseCodeSpec]:

    section_elements = driver.find_elements(by=By.CSS_SELECTOR, value="section")[2:7]

    response_code_list = list()

    for sec_elem in section_elements:
        type = sec_elem.find_element(by=By.CSS_SELECTOR, value="h2 a").text
        print(f" --- parsing section: {type}")
        section_contents = sec_elem.find_elements(by=By.CSS_SELECTOR, value="div dl dt")
        for list_elem in section_contents:
            try:
                a_element = list_elem.find_element(by=By.CSS_SELECTOR, value="a")
                code_element = a_element.find_element(by=By.CSS_SELECTOR, value="code")
                link = a_element.get_property("href")
            except NoSuchElementException as e:
                code_element = a_element.find_element(by=By.CSS_SELECTOR, value="code")
                link = "null"

            dd_elem = next_element(driver, list_elem)
            desc = dd_elem.find_element(by=By.CSS_SELECTOR, value="p").text
            name = code_element.text
            res = ResponseCodeSpec(type, name, desc, link)
            response_code_list.append(res)
            print(res)

    return response_code_list


def write_spec(response_code_list: List[ResponseCodeSpec]) -> None:
    with open("http-response-code-spec.json", "w") as fd:
        json_result = json.dumps([x.to_map() for x in response_code_list], indent=4)
        fd.write(json_result)


def main():
    website_to_parse = sys.argv[1]
    web_driver_path = sys.argv[2]
    driver = Config.get_web_driver(website_to_parse, web_driver_path)
    response_code_list = parse_content(driver)
    write_spec(response_code_list)
    pass


if __name__ == '__main__':
    main()
