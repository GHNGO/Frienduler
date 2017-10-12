<html>

<head>
    <title>Add a number</title>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/numbers.js"></script>
</head>

<body>

    <div>
        <div>
            <label>Numbers added</label>
            <table border="1">            
                <tr>
                    <td>Number</td>
                </tr>
                <#list numbers as number>
                        <tr>
                            <td>${number.num}</td>
                        </tr>
                </#list>
            </table>
        </div>
        
        <hr>
        
        <div>
            <label>Add User</label>
            <table border="1">
                <tr>
                    <td>Number</td>
                    <td>Add</td>
                </tr>                
                <tr>
                    <td><input type="text" id="input_number"></td>
                    <td><button onclick="addNumber()">Add</button></td>
                </tr>
            </table>
        </div>

        <hr>

        <div>
            <button onclick="getAverage()">Get Average</button>
            <div id="id_average"></div>
        </div>

    </div>
    
</body>

</html>