import { useEffect, useState } from 'react'
import axios from './utils/axios';
import './App.css'

enum Status {
  NEW = "NEW",
  IN_PROGRESS = "IN_PROGRESS",
  COMPLETE = "COMPLETE",
}

function App() {
  const [name, setName] = useState<string>('');
  const [items, setItems] = useState<{ name: string, id: number, status: Status}[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(false);


  useEffect(() => {
    fetchItems(); 
  }, []);

  async function fetchItems() {
    try {
      setIsLoading(true);
      const response = await axios('/item/all');
      setItems(response.data);
    } catch (error) {
      console.error('Error fetching items:', error);
    } finally {
      setIsLoading(false);
    }
  }

  async function submit() {
    await axios.put('/item', { name });
    fetchItems();
    setName('');
  }

  async function clear() {
    const clearConfirmed = window.confirm('Are you sure you want to clear all items?');
    if (clearConfirmed) {
      await axios.delete('/item/all');
      fetchItems();
    }
  }

  async function update(id: number, status: string) {
    await axios.patch(`/item/${id}`, { status });
    fetchItems();
  }

  function getBtnColorFor(btn: Status, status: Status) {
    if (btn === status) {
      return 'bg-violet-400';
    }
    return 'bg-violet-100';
  }

  return (
    <div className="flex flex-col items-center w-full">
      <h1 className="p-5 text-3xl text-bold">My Todo List</h1>
      <hr className="h-2 w-full" />
      <div className="mt-4 w-[450px]">
        <div className="flex flex-col">
          <input
            autoFocus
            onKeyDown={(e: React.KeyboardEvent) => {
              if (e.key === "Enter") {
                submit()
              }
            }}
            className="p-1 border-black border-2 rounded-md" value={name} onChange={(e) => setName(e.target.value)} type="text" placeholder={"Add Todo"}/>
            <div className="flex flex-row justify-around">
              <button
                className="inline-flex mt-2 items-center rounded-md border border-transparent bg-indigo-600 px-3 py-2 text-sm font-medium leading-4 text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
                onClick={submit}>
                Add Item
              </button>
              <button
                className="inline-flex mt-2 items-center rounded-md border border-transparent bg-violet-400 px-3 py-2 text-sm font-medium leading-4 text-white shadow-sm hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2"
                onClick={clear}>
                Clear Items
              </button>
          </div>
        </div>
        <ul className={`mt-2 py-5 ${isLoading ? 'blur bg-opacity-70' : ''}`}>
            {items?.map((item) => {
            return (
              <li key={item.id} className="border-[1px] border-gray-600 rounded-md my-5 p-2 bg-slate-200">
                <p>{item.name}</p>
                <div className="space-x-2">
                  <button
                    type="button"
                    onClick={() => update(item.id, Status.NEW)}
                    className={`inline-flex items-center rounded border border-gray-300 px-2.5 py-1.5 text-xs font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 ${getBtnColorFor(Status.NEW, item.status)}`}
                  >
                    New
                  </button>
                  <button
                    type="button"
                    onClick={() => update(item.id, Status.IN_PROGRESS)}
                    className={`inline-flex items-center rounded border border-gray-300 px-2.5 py-1.5 text-xs font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 ${getBtnColorFor(Status.IN_PROGRESS, item.status)}`}
                  >
                    In Progress
                  </button>
                  <button
                    type="button"
                    onClick={() => update(item.id, Status.COMPLETE)}
                    className={`inline-flex items-center rounded border border-gray-300 px-2.5 py-1.5 text-xs font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 ${getBtnColorFor(Status.COMPLETE, item.status)}`}
                  >
                    Complete
                  </button>
                </div>
              </li>
            )}
          )}
        </ul>
        {items?.length === 0 && !isLoading && <p className="text-gray-600 italic">No items</p>}
      </div>
    </div>
  )
}

export default App
