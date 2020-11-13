# SimpleQRCodeGenerator
The QR Code Generating API that is simple.

## Endpoints
### /qr/generate

#### Method
GET

#### Query
* value  
`require`  
URL, message, etc ...  
    * type : String

* image  
`optional`  
The URL of image that is displayed to center in qr code.
    * type : String
    
*ATTENTION*  
When using url parameters to queries, it should do percent-encoding.  

#### Response
`Content-Type` image/png
    
## Example
Generating QR code of a URL.  

```text
/qr/generate?value=https%3A%2F%2Fcyan-0fbcf9.com
```

Have displaying an image to center on QR code.
```text
/qr/generate?value=https%3A%2F%2Fcyan-0fbcf9.com&image=<image-url>
```

**Generated QR Code**  
*Sample*  
<img src="./src/main/resources/static/samples/generate.png" alt="sample" width=256 />

## Detailed QR Code Specification
### QR Version
version **6**

### Error Correction Level
**Q**  ( about 25 % )


### How much ?
alphanumeric character : *108* words  
numeric only : *178* words  
