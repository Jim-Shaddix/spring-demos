
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.chrome.service import Service
from selenium import webdriver


class Config:

    def __init__(self):
        pass

    @staticmethod
    def get_web_driver(website_to_parse: str, web_driver_path: str) -> webdriver:

        # Web Driver Configurations
        options = Options()
        options.add_argument('--headless')
        service = Service(executable_path=web_driver_path)
        driver = webdriver.Chrome(service=service, options=options)

        # Perform search
        driver.get(website_to_parse)

        return driver

