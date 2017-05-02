Made by Adam Lewandowski, Teodor Costica

We have used the exercise from class since it has all the infrastructure we needed.
We mocked the db connection using mockito, we used other tools for example mocking resultsets in
order to imitate the real db behaviour.

We made some architectural changes such as modifying the singleton to
use dependency injection so we can make it easier and more efficent to test the app with mockito.


