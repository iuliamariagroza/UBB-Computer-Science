using System.Net;
using System.Net.Sockets;
using System.Text;

sealed class SocketHelper{
    public const int BufferSize = 1024; 
     public Socket Socket { get; init; }

    public int ID { get; init; }
    public string HostName { get; init; }
    public string EndPoint { get; init; }
    public IPEndPoint IPEndPoint { get; init; }
    public byte[] Buffer { get; } = new byte[BufferSize];
    
    public StringBuilder Response { get; } = new();

    public CountdownEvent countdownEvent { get; init; }

    public ManualResetEvent ConnectDone { get; } = new(false);
    public ManualResetEvent SendDone { get; } = new(false);
    public ManualResetEvent ReceiveDone { get; } = new(false);

    public static SocketHelper New(string host, int id, CountdownEvent cde=null){
        var hostName = host.Split('/')[0];
        var hostEntry = Dns.GetHostEntry(hostName);
        var ipAddr = hostEntry.AddressList[0];
        var remoteEndpoint = new IPEndPoint(ipAddr,Parser.port);

        var socket = new Socket(ipAddr.AddressFamily, SocketType.Stream, ProtocolType.Tcp);

        return new SocketHelper
        {
            Socket = socket,
            HostName = hostName,
            EndPoint = host.Contains('/') ? host[host.IndexOf('/')..] : "/",
            IPEndPoint = remoteEndpoint,
            ID = id,
            countdownEvent = cde
        };
    }
}