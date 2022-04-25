
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.chrome.service import Service
from selenium import webdriver


class Config:

    CONTENT_DIRECTORY = "parsed-content"
    HEADER_SPEC_FILE = CONTENT_DIRECTORY + "/" + "http-header-spec.json"
    RETURN_CODE_FILE = CONTENT_DIRECTORY + "/" + "http-response-code-spec.json"
    REQUEST_METHOD_FILE = CONTENT_DIRECTORY + "/" + "http-request-method-spec.json"

    @staticmethod
    def get_webdriver(website_to_parse: str, web_driver_path: str) -> webdriver:

        # Web Driver Configurations
        options = Options()
        options.add_argument('--headless')
        service = Service(executable_path=web_driver_path)
        driver = webdriver.Chrome(service=service, options=options)

        # Perform search
        driver.get(website_to_parse)

        return driver

