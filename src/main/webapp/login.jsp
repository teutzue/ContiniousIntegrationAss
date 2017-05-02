<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"> 
    <head>
        <title>Cupcake</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>

        <form action="Control" method="POST">
            <input type="text" name="username" value="" placeholder="" />
            <input type="password" name="password" value="" placeholder="" />
            <input type="hidden" name="origin" value="login" />
            <input class="btn btn-primary" type="submit" value="Log in" name="Login" />
        </form>

    </body>
</html>