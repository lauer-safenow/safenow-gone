```toml
name = 'updateVacaction'
method = 'PATCH'
url = 'http://localhost:8080/vacations/{id}'
sortWeight = 5000000
id = 'c812685e-6a01-4d8c-aa1f-13436bd7e0e6'

[[pathVariables]]
key = 'id'
value = '452c9a70-9b1d-4514-8bdb-9ab72f55a6e1'

[body]
type = 'JSON'
raw = '''
{
  "status": "APPROVED"
}'''
```
