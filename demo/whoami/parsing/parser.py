from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.chrome.service import Service
from selenium import webdriver
from selenium.webdriver.remote.webelement import WebElement
from selenium.webdriver.chrome.webdriver import WebDriver
import json
import time
import sys
from typing import List, Dict

WEBSITE_TO_PARSE = sys.argv[1]
WEB_DRIVER_PATH = sys.argv[2]


class HeaderDesc:

    def __init__(self, header_type, header_name, header_desc, link):
        self.header_type = header_type
        self.header_name = header_name
        self.header_desc = header_desc
        self.link = link

    def to_map(self) -> Dict[str, str]:
        return {
            "header-type": self.header_type,
            "header-name": self.header_name,
            "header-desc": self.header_desc,
            "link": self.link
        }

    def __str__(self):
        return f"Header=[header-type:{self.header_type}, name:{self.header_name}, link:{self.link}, description:{self.header_desc}]"

    def __repr__(self):
        return self.__str__()


def next_element(driver: WebDriver, elem: WebElement) -> WebElement:
    next_sibiling = driver.execute_script("""
    return arguments[0].nextElementSibling
    """, elem)
    return next_sibiling


def parse_content(driver) -> List[HeaderDesc]:
    # Start Search
    driver.get(WEBSITE_TO_PARSE)

    # Parse the results of the search
    header_list = []
    header_type_elems = driver.find_elements(By.CSS_SELECTOR, "h2")[2:-6]
    for header_type_elem in header_type_elems:
        header_type = header_type_elem.text
        div_after_header_type_elem = next_element(driver, header_type_elem)
        header_link_elems = div_after_header_type_elem.find_elements(By.CSS_SELECTOR, "dt a")
        header_desc_elems = div_after_header_type_elem.find_elements(By.CSS_SELECTOR, "dd p")
        for link_elem, desc_elem in zip(header_link_elems, header_desc_elems):
            name = link_elem.text
            desc = desc_elem.text
            link = link_elem.get_attribute("href")
            header_desc = HeaderDesc(header_type, name, desc, link)
            print(header_desc)
            header_list.append(header_desc)

    return header_list


def write_header_spec(header_list: List[HeaderDesc]) -> None:
    with open("http_header_spec.json", "w") as fd:
        json_result = json.dumps([x.to_map() for x in header_list], indent=4)
        fd.write(json_result)


def main():
    # Web Driver Configurations
    options = Options()
    options.add_argument('--headless')
    service = Service(executable_path=WEB_DRIVER_PATH)
    driver = webdriver.Chrome(service=service, options=options)

    start_time = time.time()
    header_list = parse_content(driver)
    write_header_spec(header_list)
    driver.quit()
    finish_time = time.time()
    total_time = round(finish_time - start_time, 4)

    print(f"\n[Finished Parsing Job]\ntime:{total_time} parsed-website: {WEBSITE_TO_PARSE}")


if __name__ == "__main__":
    main()
