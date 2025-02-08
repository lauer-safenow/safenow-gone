```toml
name = 'updateVacaction'
method = 'PUT'
url = 'http://localhost:8080/vacations/{id}'
sortWeight = 5000000
id = 'c812685e-6a01-4d8c-aa1f-13436bd7e0e6'

[[pathVariables]]
key = 'id'
value = '5acb7789-9bc6-472b-ada0-0c3005414b76'

[body]
type = 'JSON'
raw = '''
{
  "from": "2025-01-02",
  "to": "2025-02-02",
  "takingEmployeeId": 0,
  "standInEmployeeId": 1,
  status: "APPROVED"
}'''
```
