from typing import Dict


class HeaderDesc:

    def __init__(self, header_type, header_name, header_desc, link):
        self.header_type = header_type
        self.header_name = header_name
        self.header_desc = header_desc
        self.link = link

    def to_map(self) -> Dict[str, str]:
        return {
            "type": self.header_type,
            "name": self.header_name,
            "description": self.header_desc,
            "link": self.link
        }

    def __str__(self):
        return f"Header=[header-type:{self.header_type}, name:{self.header_name}, link:{self.link}, description:{self.header_desc}]"

    def __repr__(self):
        return self.__str__()
