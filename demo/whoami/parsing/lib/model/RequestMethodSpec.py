from typing import Dict


class RequestMethodSpec:

    def __init__(self, rm_name, rm_desc, link):
        self.rm_name = rm_name
        self.rm_desc = rm_desc
        self.link = link

    def to_map(self) -> Dict[str, str]:
        return {
            "name": self.rm_name,
            "description": self.rm_desc,
            "link": self.link
        }

    def __str__(self):
        return f"ResponseCodeSpec=[name:{self.rm_name}, link:{self.link}, description:{self.rm_desc}]"

    def __repr__(self):
        return self.__str__()
