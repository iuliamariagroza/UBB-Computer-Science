Directory.CreateDirectory("ResultFiles");

List<string> urls = new() {
    "cs.ubbcluj.ro/~rlupsa/edu/pdp/lab-4-futures-continuations.html",
    "dexonline.ro/contact",
};

Console.WriteLine("Callbacks");
CallbackMethod.Run(urls);

Thread.Sleep(2500);

Console.WriteLine("\n\n\n\nTask");
TaskMethod.Run(urls);

Thread.Sleep(2500);

Console.WriteLine("\n\n\n\nAsync");
AsyncMethod.Run(urls);