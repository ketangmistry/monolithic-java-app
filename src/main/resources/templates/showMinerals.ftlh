<!DOCTYPE html>
<html>
    <head>
        <title>Minerals App</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <script>
            function getRandomInt(max) {
                return Math.floor(Math.random() * Math.floor(max));
            }


            function purchase() {
                var xhttp = new XMLHttpRequest();
                var minerals = Array("Adamantium","Carbonite","Chronoton","Flubber","Kryptonite","Vibranium");
                var mineral = minerals[Math.floor(Math.random() * minerals.length)];

                var amount = getRandomInt(276834);

                xhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("status").innerHTML = this.responseText;
                    }
                };
                xhttp.open("PUT", "/minerals/" + mineral + "/" + amount, true);
                xhttp.send();

            }
        </script>


        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <h2>Fictious Minerals</h2>

        <table>
            <tr>
                <th>Id</th>
                <th>Mineral Name</th>
                <th>Purchase Total</th>
            </tr>

            <#list minerals as mineral>
                <tr>
                    <td>${mineral.id}</td>
                    <td>${mineral.name}</td>
                    <td>${mineral.purchases}</td>
                </tr>
            </#list>
        </table>

        <br/>
        <button onclick="purchase()">Purchase Top Mineral</button>
        <br/>
        <span id="status"></span>

    </body>
</html>
