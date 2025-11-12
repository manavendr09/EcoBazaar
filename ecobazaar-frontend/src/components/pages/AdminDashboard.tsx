import { StatCard } from "../StatCard";
import {
  DollarSign,
  Users,
  ShoppingBag,
  TrendingUp,
  Package,
  Calendar,
  BarChart3,
  Settings,
} from "lucide-react";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "../ui/tabs";
import { LineChart, Line, BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from "recharts";

const salesData = [
  { month: "Jan", sales: 4500, users: 240 },
  { month: "Feb", sales: 5200, users: 320 },
  { month: "Mar", sales: 6100, users: 380 },
  { month: "Apr", sales: 5800, users: 420 },
  { month: "May", sales: 7200, users: 510 },
  { month: "Jun", sales: 8500, users: 620 },
];

const topProducts = [
  { name: "Bamboo Toothbrush", sales: 1250, revenue: 16237.5 },
  { name: "Reusable Water Bottle", sales: 980, revenue: 24475.2 },
  { name: "Organic Cotton Bag", sales: 876, revenue: 16632.24 },
  { name: "Eco Cleaning Kit", sales: 654, revenue: 19603.46 },
  { name: "Sustainable Cutlery", sales: 543, revenue: 8682.57 },
];

const recentOrders = [
  {
    id: "#ECO-1234",
    customer: "Emma Green",
    product: "Bamboo Toothbrush Set",
    amount: 12.99,
    status: "Delivered",
  },
  {
    id: "#ECO-1235",
    customer: "John Doe",
    product: "Reusable Water Bottle",
    amount: 24.99,
    status: "Shipped",
  },
  {
    id: "#ECO-1236",
    customer: "Sarah Climate",
    product: "Organic Cotton Bag",
    amount: 18.99,
    status: "Processing",
  },
  {
    id: "#ECO-1237",
    customer: "Mike Sustain",
    product: "Eco Cleaning Kit",
    amount: 29.99,
    status: "Delivered",
  },
];

export function AdminDashboard() {
  return (
    <div className="min-h-screen bg-[#f5f5dc]/30">
      <div className="flex">
        {/* Sidebar */}
        <aside className="hidden lg:block w-64 bg-white border-r border-border min-h-screen sticky top-16">
          <div className="p-6">
            <h3 className="text-foreground mb-4">Admin Panel</h3>
            <nav className="space-y-2">
              {[
                { icon: BarChart3, label: "Dashboard", active: true },
                { icon: Users, label: "Users" },
                { icon: Package, label: "Products" },
                { icon: ShoppingBag, label: "Orders" },
                { icon: Calendar, label: "Events" },
                { icon: TrendingUp, label: "Analytics" },
                { icon: Settings, label: "Settings" },
              ].map((item) => (
                <button
                  key={item.label}
                  className={`w-full flex items-center gap-3 px-4 py-3 rounded-xl transition-colors ${
                    item.active
                      ? "bg-[#2E8B57] text-white"
                      : "text-muted-foreground hover:bg-[#f5f5dc]"
                  }`}
                >
                  <item.icon className="w-5 h-5" />
                  <span>{item.label}</span>
                </button>
              ))}
            </nav>
          </div>
        </aside>

        {/* Main Content */}
        <main className="flex-1 p-8">
          <div className="max-w-7xl mx-auto">
            <div className="mb-8">
              <h1 className="text-foreground mb-2">Dashboard Overview</h1>
              <p className="text-muted-foreground">Welcome back! Here's what's happening with EcoBazaarX today.</p>
            </div>

            {/* Stats Grid */}
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
              <StatCard
                title="Total Revenue"
                value="$85,420"
                icon={DollarSign}
                trend="+12.5%"
                trendUp={true}
              />
              <StatCard
                title="Active Users"
                value="50,234"
                icon={Users}
                trend="+8.2%"
                trendUp={true}
              />
              <StatCard
                title="Total Orders"
                value="4,302"
                icon={ShoppingBag}
                trend="+23.1%"
                trendUp={true}
              />
              <StatCard
                title="Products Sold"
                value="12,567"
                icon={Package}
                trend="+15.3%"
                trendUp={true}
              />
            </div>

            {/* Charts */}
            <div className="grid grid-cols-1 lg:grid-cols-2 gap-6 mb-8">
              <div className="bg-white rounded-2xl p-6 shadow-sm border border-border">
                <h3 className="text-foreground mb-4">Sales Overview</h3>
                <ResponsiveContainer width="100%" height={300}>
                  <LineChart data={salesData}>
                    <CartesianGrid strokeDasharray="3 3" stroke="#e0e0e0" />
                    <XAxis dataKey="month" stroke="#666666" />
                    <YAxis stroke="#666666" />
                    <Tooltip />
                    <Line
                      type="monotone"
                      dataKey="sales"
                      stroke="#2E8B57"
                      strokeWidth={2}
                      dot={{ fill: "#2E8B57" }}
                    />
                  </LineChart>
                </ResponsiveContainer>
              </div>

              <div className="bg-white rounded-2xl p-6 shadow-sm border border-border">
                <h3 className="text-foreground mb-4">User Growth</h3>
                <ResponsiveContainer width="100%" height={300}>
                  <BarChart data={salesData}>
                    <CartesianGrid strokeDasharray="3 3" stroke="#e0e0e0" />
                    <XAxis dataKey="month" stroke="#666666" />
                    <YAxis stroke="#666666" />
                    <Tooltip />
                    <Bar dataKey="users" fill="#2E8B57" radius={[8, 8, 0, 0]} />
                  </BarChart>
                </ResponsiveContainer>
              </div>
            </div>

            {/* Tables */}
            <Tabs defaultValue="products" className="mb-8">
              <TabsList className="mb-6">
                <TabsTrigger value="products">Top Products</TabsTrigger>
                <TabsTrigger value="orders">Recent Orders</TabsTrigger>
              </TabsList>

              <TabsContent value="products">
                <div className="bg-white rounded-2xl shadow-sm border border-border overflow-hidden">
                  <div className="overflow-x-auto">
                    <table className="w-full">
                      <thead className="bg-[#f5f5dc] border-b border-border">
                        <tr>
                          <th className="px-6 py-4 text-left text-foreground">Product Name</th>
                          <th className="px-6 py-4 text-left text-foreground">Sales</th>
                          <th className="px-6 py-4 text-left text-foreground">Revenue</th>
                          <th className="px-6 py-4 text-left text-foreground">Status</th>
                        </tr>
                      </thead>
                      <tbody>
                        {topProducts.map((product, index) => (
                          <tr key={index} className="border-b border-border hover:bg-[#f5f5dc]/30 transition-colors">
                            <td className="px-6 py-4 text-foreground">{product.name}</td>
                            <td className="px-6 py-4 text-muted-foreground">{product.sales}</td>
                            <td className="px-6 py-4 text-[#2E8B57]">
                              ${product.revenue.toFixed(2)}
                            </td>
                            <td className="px-6 py-4">
                              <span className="px-3 py-1 bg-[#2E8B57]/10 text-[#2E8B57] rounded-full">
                                In Stock
                              </span>
                            </td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </div>
                </div>
              </TabsContent>

              <TabsContent value="orders">
                <div className="bg-white rounded-2xl shadow-sm border border-border overflow-hidden">
                  <div className="overflow-x-auto">
                    <table className="w-full">
                      <thead className="bg-[#f5f5dc] border-b border-border">
                        <tr>
                          <th className="px-6 py-4 text-left text-foreground">Order ID</th>
                          <th className="px-6 py-4 text-left text-foreground">Customer</th>
                          <th className="px-6 py-4 text-left text-foreground">Product</th>
                          <th className="px-6 py-4 text-left text-foreground">Amount</th>
                          <th className="px-6 py-4 text-left text-foreground">Status</th>
                        </tr>
                      </thead>
                      <tbody>
                        {recentOrders.map((order, index) => (
                          <tr key={index} className="border-b border-border hover:bg-[#f5f5dc]/30 transition-colors">
                            <td className="px-6 py-4 text-foreground">{order.id}</td>
                            <td className="px-6 py-4 text-muted-foreground">{order.customer}</td>
                            <td className="px-6 py-4 text-muted-foreground">{order.product}</td>
                            <td className="px-6 py-4 text-[#2E8B57]">${order.amount}</td>
                            <td className="px-6 py-4">
                              <span
                                className={`px-3 py-1 rounded-full ${
                                  order.status === "Delivered"
                                    ? "bg-[#2E8B57]/10 text-[#2E8B57]"
                                    : order.status === "Shipped"
                                    ? "bg-blue-100 text-blue-600"
                                    : "bg-yellow-100 text-yellow-600"
                                }`}
                              >
                                {order.status}
                              </span>
                            </td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </div>
                </div>
              </TabsContent>
            </Tabs>
          </div>
        </main>
      </div>
    </div>
  );
}
